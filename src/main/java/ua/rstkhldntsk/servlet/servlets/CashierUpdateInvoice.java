package ua.rstkhldntsk.servlet.servlets;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/updateInvoice")
public class CashierUpdateInvoice extends HttpServlet {

    InvoiceService invoiceService = InvoiceService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        Invoice invoice = (Invoice) session.getAttribute("invoice");
        String status = (String) session.getAttribute("status");
        invoice.setStatus(status);
        if (status.equals("DECLINED")) {
            invoiceService.updateInvoice(invoice);
            session.removeAttribute("invoice");
            session.setAttribute("message", resourceBundle.getString("invoice.is.declined"));
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        } else if (status.equals("CLOSED")) {
            invoiceService.updateInvoice(invoice);
            session.removeAttribute("invoice");
            session.setAttribute("message", resourceBundle.getString("invoice.is.closed"));
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        }
    }

}
