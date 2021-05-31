package ua.rstkhldntsk.servlet.services;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.exceptions.ItemExistException;
import ua.rstkhldntsk.servlet.exceptions.NotEnoughProduct;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;

import java.math.BigDecimal;
import java.util.List;

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
        try {
            invoiceDao.create(invoice);
        } catch (ItemExistException e) {
            e.printStackTrace();
        }
    }

    public void addProductToInvoice(String code, String quantity, Invoice invoice, BigDecimal price) throws NotEnoughProduct, ItemExistException {
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

    public void updateInvoice(Invoice invoice) {
//        invoice = invoiceDAO.findById(invoice.getId()).get();
//        invoiceDAO.updateTotal(invoice);
//        invoiceDAO.updateStatus(invoice);
        invoiceDAO.update(invoice);
    }

//    public void closeInvoice(Invoice invoice) {
//        invoice = invoiceDAO.findById(invoice.getId()).get();
//        invoiceDAO.updateTotal(invoice);
//        invoiceDAO.updateStatus(invoice);
//    }

    public void deleteInvoice(Invoice invoice) {
        invoiceDAO.delete(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceDAO.findAll();
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

    public List<Invoice> findAllInvoices() {
        return invoiceDAO.findAll();
    }

    public Invoice findById(Long id) {
        return invoiceDAO.findById(id).get();
    }

}
