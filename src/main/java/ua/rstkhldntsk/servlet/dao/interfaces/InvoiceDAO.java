package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceDAO extends GenericDAO<Invoice> {

    /**
     * finds all users receipts
     *
     * @param user user
     * @return list of users receipts
     */
    List<Invoice> findAllByUser(User user);

    void addProduct(Long code, Integer quantity, Invoice invoice, BigDecimal price);

    boolean updateStatusToClosed(Invoice invoice);

    Invoice findByUserAndInvoiceId(User user, Invoice invoice);

    boolean updateTotal(Invoice invoice);
}
