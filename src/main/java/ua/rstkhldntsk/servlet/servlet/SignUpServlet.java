package ua.rstkhldntsk.servlet.servlet;

import ua.rstkhldntsk.servlet.model.Role;
import ua.rstkhldntsk.servlet.model.User;
import ua.rstkhldntsk.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.addUser(user);


    }

}
