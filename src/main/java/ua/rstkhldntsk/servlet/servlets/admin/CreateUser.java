package ua.rstkhldntsk.servlet.servlets.admin;

import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.utils.Encoder;
import ua.rstkhldntsk.servlet.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/createUser")
public class CreateUser extends HttpServlet {

    UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<User> users = userService.findAll();
//        req.setAttribute("usersFromServer", users);
        resp.sendRedirect("/Servlet_war/home.jsp");
//        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = Encoder.encodePassword(req.getParameter("password"));
        String role = req.getParameter("role");

        userService.create(new User(username, password, role));
        req.getRequestDispatcher("/home.jsp").forward(req,resp);
//        doGet(req, resp);
    }
}
