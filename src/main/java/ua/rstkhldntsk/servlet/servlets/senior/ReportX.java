package ua.rstkhldntsk.servlet.servlets.senior;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.InvoiceService;
import ua.rstkhldntsk.servlet.services.XReportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reportX")
public class ReportX extends HttpServlet {

    InvoiceService invoiceService = new InvoiceService();
    XReportService xReport = new XReportService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Invoice> invoices = invoiceService.getAllUserInvoices(user);
        int countOfInvoices = invoices.size();
        double total = invoices.stream().mapToDouble(s -> s.getTotal().doubleValue()).sum();
        xReport.makeReport(countOfInvoices, total,req);
        req.getRequestDispatcher("/xReport.jsp").forward(req, resp);
    }
}
