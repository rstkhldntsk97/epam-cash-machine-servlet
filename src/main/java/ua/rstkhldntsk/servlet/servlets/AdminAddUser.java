package ua.rstkhldntsk.servlet.servlets;

import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addUser")
public class AdminAddUser extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        userService.create(new User(username, password, role));

        doGet(req, resp);
    }
}
