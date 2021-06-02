package ua.rstkhldntsk.servlet.services;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.exceptions.NotEnoughProduct;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.models.User;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceService {

    private static volatile InvoiceService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    InvoiceDAO invoiceDAO = daoFactory.createInvoiceDao();
    ProductDAO productDAO = daoFactory.createProductDao();
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
        try {
            invoiceDAO.create(invoice);
        } catch (ProductAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    public void addProductToInvoice(Long code, Integer quantity, Invoice invoice, BigDecimal price) throws NotEnoughProduct, ProductAlreadyExistException {
        Product product = productDAO.findByCode(code).get();
        if (product.getQuantity() >= quantity) {
            invoiceDAO.addProduct(code, quantity, invoice, price);
            product.setQuantity(product.getQuantity() - quantity);
            productDAO.update(product);
        } else {
            LOGGER.debug("Product is not available in this quantity");
            throw new NotEnoughProduct();
        }
    }

    public void updateInvoice(Invoice invoice) {
        invoiceDAO.update(invoice);
    }


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

    public List<Invoice> findAllUserInvoices(User user) {
        return invoiceDAO.findAllByUser(user);
    }

    public Invoice findById(Long id) {

        return invoiceDAO.findById(id).get();
    }

}
