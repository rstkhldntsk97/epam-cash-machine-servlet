package ua.rstkhldntsk.servlet.servlets;

import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.utils.Encoder;
import ua.rstkhldntsk.servlet.services.UserService;
import ua.rstkhldntsk.servlet.utils.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int page = 1;
        if(req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        Page<User> users = userService.findAllByPage(page);
        req.setAttribute("usersFromServer", users.getContent());
        req.setAttribute("noOfPages", users.getTotalPages());
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = Encoder.encodePassword(req.getParameter("password"));
        String role = req.getParameter("role");
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        try {
            userService.create(new User(username, password, role));
            session.setAttribute("message", resourceBundle.getString("create.user.success"));
        } catch (ProductAlreadyExistException e) {
            session.setAttribute("message", resourceBundle.getString("create.user.exist.exc"));
        }
        resp.sendRedirect(req.getContextPath() + "/createUser.jsp");
    }
}
