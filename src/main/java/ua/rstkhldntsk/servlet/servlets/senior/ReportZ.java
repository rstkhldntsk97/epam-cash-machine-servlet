package ua.rstkhldntsk.servlet.servlets.senior;

import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reportZ")
public class ReportZ extends HttpServlet {

    InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

    }
}
