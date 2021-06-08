package ua.rstkhldntsk.servlet.servlets;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.InvoiceService;
import ua.rstkhldntsk.servlet.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/logout")
public class LogOut extends HttpServlet {

    InvoiceService invoiceService = InvoiceService.getInstance();
    private static final Logger LOGGER = Logger.getLogger(LogOut.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole().equals("CASHIER")) {
            List<Invoice> invoices = invoiceService.findAllUserInvoices(user);
            invoices.forEach(invoice -> {
                if (invoice.getStatus().equals("NEW")) {
                    invoiceService.deleteInvoice(invoice);
                }
            });
            LOGGER.info("all unclosed invoices were deleted");
        }
        session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
        LOGGER.info(user + " is successfully logged out");
    }

}
