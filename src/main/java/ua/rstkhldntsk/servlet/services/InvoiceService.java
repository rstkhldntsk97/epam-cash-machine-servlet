package ua.rstkhldntsk.servlet.services;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.exceptions.*;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.utils.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class InvoiceService {

    private static volatile InvoiceService instance;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
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
        invoiceDAO.create(invoice);
    }

    public void addProductToInvoice(Integer code, Integer quantity, Invoice invoice, BigDecimal price, Integer langId) throws NotEnoughProduct, ProductAlreadyExistException {
        Product product = productDAO.findById(code, langId).get();
        if (product.getQuantity() >= quantity) {
            invoiceDAO.addProduct(code, quantity, invoice, price);
            product.setQuantity(product.getQuantity() - quantity);
            productDAO.update(product);
        } else {
            LOGGER.debug("Product is not available in this quantity");
            throw new NotEnoughProduct();
        }
    }

    public void deleteProductFromInvoice(String productName1, Integer invoiceId, String lang) throws ProductNotExist, InvalidInput {
        Integer langId = Validator.languageValidate(lang);
        String productName = Validator.productNameOnEngValidate(productName1);
        Optional<Product> productOptional = productDAO.findByName(productName, langId);
        Invoice invoice = invoiceDAO.findById(invoiceId, 1).get();
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
//            Integer quan = invoice.getProducts().get(product);
//            product.setQuantity(product.getQuantity() + quan);
            invoiceDAO.deleteProductFromInvoice(product.getCode(), invoiceId);
            productDAO.update(product);
            if ((long) invoice.getProducts().keySet().size() > 1) {
                invoiceDAO.update(invoice);
                LOGGER.debug(product.getName() + " deleted");
            } else {
                invoiceDAO.delete(invoice);
                LOGGER.debug("Product " + product.getCode() + " and invoice " + invoice.getId() + " were deleted");
            }
        } else {
            LOGGER.error("This product isn't in this invoice");
            throw new ProductNotExist();
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

    public BigDecimal countPriceForProductByQuantity(Integer quantity, Integer productCode, Integer langId) throws InvalidInput {
        try {
            Product product = productDAO.findById(productCode, langId).get();
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

    public Invoice findById(String invoiceId, String lang) throws IdNotExist, InvalidInput {
        Integer id = Validator.invoiceIdValidate(invoiceId);
        Integer langId = Validator.languageValidate(lang);
        Optional<Invoice> invoice = invoiceDAO.findById(id, langId);
        if (invoice.isPresent()) {
            if (invoice.get().getId() != null) {
                return invoice.get();
            }
            LOGGER.error("wrong id");
            throw new IdNotExist();
        }
        LOGGER.error("wrong id");
        throw new IdNotExist();
    }

}
