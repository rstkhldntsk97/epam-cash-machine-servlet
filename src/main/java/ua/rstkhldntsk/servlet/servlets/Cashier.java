package ua.rstkhldntsk.servlet.servlets;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/cashier")
public class Cashier extends HttpServlet {

    InvoiceService invoiceService = InvoiceService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        session.setAttribute("invoice", invoice);
        invoiceService.createNewInvoice(invoice);
        resp.sendRedirect(req.getContextPath() + "/createInvoice.jsp");
    }
}
