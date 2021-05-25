package ua.rstkhldntsk.servlet.servlet;

import ua.rstkhldntsk.servlet.model.Role;
import ua.rstkhldntsk.servlet.model.User;
import ua.rstkhldntsk.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/adminPage")
public class AdminServlet extends HttpServlet {

    UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findAll();
        req.setAttribute("usersFromServer", users);
        req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));

        userService.create(new User(username, password, role));

        doGet(req, resp);
    }
}
