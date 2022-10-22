package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signUp", value = "/signup")
@Slf4j
public class SignUp extends HttpServlet {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private final String pathFileJsp = "/WEB-INF/jsp/";

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext applicationCOntext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = applicationCOntext.getBean(UserService.class);
        this.passwordEncoder = applicationCOntext.getBean(PasswordEncoder.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(pathFileJsp + "signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        user.setPhone(req.getParameter("phone"));
        user.setPassword(passwordEncoder.encode(req.getParameter("password")));
        user.setEmail(req.getParameter("email"));

        int status = userService.signUp(user);

        if (status == 1) {
            resp.sendRedirect("signin");
        } else {
            req.setAttribute("errorMsg", "This email address already exists");
            req.getRequestDispatcher(pathFileJsp + "signUp.jsp").forward(req, resp);
        }
    }
}
