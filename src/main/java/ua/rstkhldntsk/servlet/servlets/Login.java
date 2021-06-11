package ua.rstkhldntsk.servlet.servlets;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.exceptions.InvalidInput;
import ua.rstkhldntsk.servlet.exceptions.UserNotExist;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.utils.Encoder;
import ua.rstkhldntsk.servlet.services.UserService;
import ua.rstkhldntsk.servlet.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/login")
public class Login extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Login.class);
    private UserService userService = new UserService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = userService.login(username, password);
            LOGGER.info("User " + username + " logged in successfully");
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        } catch (UserNotExist userNotExist) {
            LOGGER.warn("There is no users with username: " + username + " and password: " + password);
            session.setAttribute("message", resourceBundle.getString("login.danger.alert"));
            resp.sendRedirect(req.getContextPath() + "/error404.jsp");
        }
    }
}
