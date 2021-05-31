package ua.rstkhldntsk.servlet.servlets;

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
import java.util.List;


@WebServlet("/logout")
public class LogOut extends HttpServlet {

    private UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InvoiceService invoiceService = InvoiceService.getInstance();
        User user = (User) req.getAttribute("user");
        if (user.getRole().equals("CASHIER")) {
            List<Invoice> invoices = invoiceService.findAllUserInvoices(user);

            invoices.forEach(invoice -> {
                if (invoice.getStatus().equals("NEW")) {
                    invoiceService.deleteInvoice(invoice);
                }
            });

        }
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
