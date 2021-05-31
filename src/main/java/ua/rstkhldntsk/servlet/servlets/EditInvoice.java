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
import java.util.List;

@WebServlet("/editInvoice")
public class EditInvoice extends HttpServlet {

    InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Invoice> invoices = invoiceService.findAllInvoices();
        req.setAttribute("invoicesFromServer", invoices);
        req.getRequestDispatcher("/editInvoice.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String invoiceId = req.getParameter("id");
        Invoice invoiceToEdit = invoiceService.findById(Long.parseLong(invoiceId));
        session.setAttribute("invoice", invoiceToEdit);
        req.setAttribute("invoice", invoiceToEdit);
        req.getRequestDispatcher("/currentInvoice.jsp").forward(req, resp);
    }
}
