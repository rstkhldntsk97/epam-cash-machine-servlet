package ua.rstkhldntsk.servlet.services;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceProductDAO;
import ua.rstkhldntsk.servlet.dao.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;

import java.util.List;

public class InvoiceService {

    private static volatile InvoiceService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    InvoiceDAO invoiceDAO = JDBCDaoFactory.getInstance().createInvoiceDao();

    public static InvoiceService getInstance() {
        if (instance == null) {
            synchronized (InvoiceService.class) {
                if (instance == null) {
                    instance = new InvoiceService();
                }
            }
        }
        return instance;
    }

    public void createNewInvoice(Invoice invoice) {
        try {
            InvoiceDAO invoiceDao = daoFactory.createInvoiceDao();
            invoiceDao.create(invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Invoice> getAllUserChecks(User user) {
        try {
            InvoiceDAO invoiceDao = daoFactory.createInvoiceDao();
            InvoiceProductDAO invoiceProductDAO = daoFactory.createInvoiceProductDao();
            List<Invoice> userInvoices = invoiceDao.findAllByUser(user);
            userInvoices.forEach(invoice -> invoice.setProducts(invoiceProductDAO.findAllByInvoice(invoice)));
            return userInvoices;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
