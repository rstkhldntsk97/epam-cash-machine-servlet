package ua.rstkhldntsk.servlet.servlets.cashier;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/closeInvoice")
public class CloseInvoice extends HttpServlet {

    InvoiceService invoiceService = InvoiceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Invoice invoice = (Invoice)session.getAttribute("invoice");


        invoiceService.closeInvoice(invoice);
        req.getRequestDispatcher("/cashier").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Invoice invoice = (Invoice)session.getAttribute("invoice");


        invoiceService.closeInvoice(invoice);

        doGet(req, resp);
    }
}
