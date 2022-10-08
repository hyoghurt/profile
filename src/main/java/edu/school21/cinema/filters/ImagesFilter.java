package edu.school21.cinema.filters;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter(value = {"/images/*"})
public class ImagesFilter implements Filter {
    private ImageService imageService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext applicationContext = (ApplicationContext) filterConfig.getServletContext().getAttribute("springContext");
        imageService = applicationContext.getBean(ImageService.class);
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        User user = (User) req.getSession().getAttribute("user");
        String[] split = req.getRequestURI().split("/");
        int len = split.length;

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else if (!split[len - 1].equals("images")
                && !imageService.authImages(user.getId(), UUID.fromString(split[len - 1]))) {

            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
