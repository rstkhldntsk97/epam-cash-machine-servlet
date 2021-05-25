package ua.rstkhldntsk.servlet.servlet;

import ua.rstkhldntsk.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = userService.login(username, password);
        if (role == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else if (role.equals("ADMIN")){
            resp.sendRedirect(req.getContextPath() + "/adminPage");
        }else if (role.equals("CASHIER")) {
            resp.sendRedirect(req.getContextPath() + "/cashierPage");
        }else if (role.equals("SENIOR_CASHIER")) {
            resp.sendRedirect(req.getContextPath() + "/seniorCashierPage");
        }else if (role.equals("COMMODITY_EXPERT")) {
            resp.sendRedirect(req.getContextPath() + "/commodityExpertPage");
        }
    }

}
