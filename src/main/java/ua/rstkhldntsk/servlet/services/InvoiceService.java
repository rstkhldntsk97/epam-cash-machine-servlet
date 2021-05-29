package ua.rstkhldntsk.servlet.services;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.JDBCInvoiceDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.exceptions.NotEnoughProduct;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.servlets.cashier.CreateInvoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InvoiceService {

    private static volatile InvoiceService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    InvoiceDAO invoiceDAO = JDBCDaoFactory.getInstance().createInvoiceDao();
    ProductDAO productDAO = JDBCDaoFactory.getInstance().createProductDao();
    private static final Logger LOGGER = Logger.getLogger(InvoiceService.class);

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
        InvoiceDAO invoiceDao = daoFactory.createInvoiceDao();
        invoiceDao.create(invoice);
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceDAO.findById(id).get();
    }

    public void addProductToInvoice(String code, String quantity, Invoice invoice, BigDecimal price) throws NotEnoughProduct {
        Long parsedCode = Long.parseLong(code);
        Integer parsedQuantity = Integer.parseInt(quantity);

        Product product = productDAO.findByCode(parsedCode).get();
        if (product.getQuantity() >= parsedQuantity) {
            InvoiceDAO invoiceDao = daoFactory.createInvoiceDao();
            invoiceDao.addProduct(parsedCode, parsedQuantity, invoice, price);
        } else {
            LOGGER.debug("Product is not available in this quantity");
            throw new NotEnoughProduct();
        }
    }

    public void closeInvoice(Invoice invoice) {
        invoice = invoiceDAO.findById(invoice.getId()).get();
        invoiceDAO.updateTotal(invoice);
        invoiceDAO.updateStatusToClosed(invoice);
    }

    public List<Invoice> getAllInvoices() {
        InvoiceDAO invoiceDao = daoFactory.createInvoiceDao();
        return invoiceDao.findAll();
    }

    public BigDecimal countPriceForProductByQuantity(Integer quantity, Long productCode) {
        try {
            Product product = productDAO.findByCode(productCode).get();
            LOGGER.debug(product.getName() + " " + product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            return product.getPrice().multiply(BigDecimal.valueOf(quantity));
        } catch (NullPointerException e) {
            throw e;
        }

    }

}
