package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "signIn", value = "/signin")
public class SignIn extends HttpServlet {
    private UserService userService;
    private final String pathFileJsp = "/WEB-INF/jsp/";

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext applicationCOntext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = applicationCOntext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher(pathFileJsp + "signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User findUser = userService.signIn(req.getParameter("phone"),
                req.getParameter("password"),
                req.getRemoteAddr(),
                new Timestamp(System.currentTimeMillis()));

        if (findUser == null) {
            req.setAttribute("errorMsg", "Incorrect login or password");
            req.getRequestDispatcher(pathFileJsp + "signIn.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", findUser);
            resp.sendRedirect("profile");
        }
    }
}
