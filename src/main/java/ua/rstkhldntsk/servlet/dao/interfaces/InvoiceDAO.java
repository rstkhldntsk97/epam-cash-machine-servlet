package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InvoiceDAO extends GenericDAO<Invoice> {

    /**
     * finds all users receipts
     *
     * @param user user
     * @return list of users receipts
     */
    List<Invoice> findAllByUser(User user);

    /**
     * adds product to invoice
     *
     * @param code product code
     * @param quantity product quantity in current invoice
     * @param invoice current invoice
     * @param price total of product in invoice
     */
    void addProduct(Integer code, Integer quantity, Invoice invoice, BigDecimal price) throws ProductAlreadyExistException;

    /**
     * finds invoice
     *
     * @param id invoice id
     * @param langId language
     * @return optional of invoice
     */
    Optional<Invoice> findById(Integer id, Integer langId);

    /**
     * deletes product from invoice
     *
     * @param productCode product code
     * @param invoiceId invoice id
     */
    boolean deleteProductFromInvoice(Integer productCode, Integer invoiceId);
}
