package ua.rstkhldntsk.servlet.servlets;

import ua.rstkhldntsk.servlet.dao.JDBCInvoiceDAO;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.InvoiceService;
import ua.rstkhldntsk.servlet.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;


@WebServlet("/admin")
public class Admin extends HttpServlet {

    UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findAll();
        req.setAttribute("usersFromServer", users);
        resp.sendRedirect("/Servlet_war/home.jsp");
//        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        userService.create(new User(username, password, role));

        doGet(req, resp);
    }
}
