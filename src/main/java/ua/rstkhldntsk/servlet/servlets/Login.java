package ua.rstkhldntsk.servlet.servlets;

import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    private UserService userService = new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(username, password);
        String role = user.getRole();
        HttpSession session = req.getSession();
        if (role == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else if (role.equals("ADMIN")){
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/admin");
        }else if (role.equals("CASHIER")) {
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/cashier");
        }else if (role.equals("SENIOR_CASHIER")) {
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/seniorCashier.jsp");
        }else if (role.equals("COMMODITY_EXPERT")) {
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/commodityExpert");
        }
    }

}
