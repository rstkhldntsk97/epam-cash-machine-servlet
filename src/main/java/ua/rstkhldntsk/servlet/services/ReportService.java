package ua.rstkhldntsk.servlet.services;


import ua.rstkhldntsk.servlet.dao.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class ReportService {

    private static volatile ReportService instance;
    InvoiceDAO invoiceDAO = JDBCDaoFactory.getInstance().createInvoiceDao();

    InvoiceService invoiceService = new InvoiceService();
    public static ReportService getInstance() {
        if (instance == null) {
            synchronized (ReportService.class) {
                if (instance == null) {
                    instance = new ReportService();
                }
            }
        }
        return instance;
    }

    public void makeXReport(HttpServletRequest request) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        int countOfInvoices = invoices.size();
        double total = invoices.stream().mapToDouble(s -> s.getTotal().doubleValue()).sum();
        request.setAttribute("COUNT", countOfInvoices);
        request.setAttribute("TOTAL_SUM", total);
    }

    public void makeZReport(HttpServletRequest request) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        User user = (User) request.getSession().getAttribute("user");
        int countOfInvoices = invoices.size();
        double total = invoices.stream().mapToDouble(s -> s.getTotal().doubleValue()).sum();
        request.setAttribute("HEAD_OF_SHIFT", user);
        request.setAttribute("COUNT", countOfInvoices);
        request.setAttribute("TOTAL_SUM", total);
        invoices.forEach(invoiceService::deleteInvoice);
    }

}
