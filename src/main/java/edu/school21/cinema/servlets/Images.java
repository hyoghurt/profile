package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "images", value = "/images/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class Images extends HttpServlet {
    private ImageService imageService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext applicationCOntext = (ApplicationContext) context.getAttribute("springContext");
        this.imageService = applicationCOntext.getBean(ImageService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part part = req.getPart("file");
            User user = (User) req.getSession().getAttribute("user");
            if (part.getSize() != 0) {
                imageService.saveFile(part, user.getId());
            }
        } catch (IllegalStateException ignored) {
        } finally {
            resp.sendRedirect("profile");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] split = req.getRequestURI().split("/");
        int len = 3;
        if (split.length > 1 && split[1].equals("cinema")) {
            len = 4;
        }

        if (split.length != len) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            byte[] image = imageService.getImageById(split[len - 1]);

            if (image == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            } else {
                resp.getOutputStream().write(image);
                resp.setContentType(imageService.getMimeById(UUID.fromString(split[len - 1])));
            }
        }
    }
}
