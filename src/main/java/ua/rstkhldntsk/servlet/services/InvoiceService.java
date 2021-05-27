package ua.rstkhldntsk.servlet.services;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.JDBCInvoiceDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;

import java.util.Optional;

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

    public Invoice getInvoiceById(Long id) {
        return invoiceDAO.findById(id).get();
    }

    public void addProductToInvoice(String code, Invoice invoice) {
        try {
            InvoiceDAO invoiceDao = daoFactory.createInvoiceDao();
            invoiceDao.addProduct(Long.parseLong(code), invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeInvoice(Invoice invoice) {
        try {
            InvoiceDAO invoiceDao = daoFactory.createInvoiceDao();
            invoiceDao.updateStatusToClosed(invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
