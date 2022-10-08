package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
import edu.school21.cinema.services.SignInUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profile", value = "/profile")
public class Profile extends HttpServlet {
    private ImageService imageService;
    private SignInUserService signInUserService;
    private final String pathFileJsp = "/WEB-INF/jsp/";

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext applicationCOntext = (ApplicationContext) context.getAttribute("springContext");
        this.imageService = applicationCOntext.getBean(ImageService.class);
        this.signInUserService = applicationCOntext.getBean(SignInUserService.class);
    }

    @Override
    @Transactional
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (resp.getStatus() != HttpServletResponse.SC_FORBIDDEN) {
            User user = (User) req.getSession().getAttribute("user");
            req.setAttribute("list_sign_in", signInUserService.getListSignInDTO(user.getId()));
            req.setAttribute("list_images", imageService.getListImages(user.getId()));
            req.getRequestDispatcher(pathFileJsp + "profile.jsp").forward(req, resp);
        }
    }
}
