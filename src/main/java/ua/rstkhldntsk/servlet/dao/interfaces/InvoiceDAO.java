package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;

import java.util.List;

public interface InvoiceDAO extends GenericDAO<Invoice> {

    /**
     * finds all users receipts
     *
     * @param user user
     * @return list of users receipts
     */
    List<Invoice> findAllByUser(User user);

    void addProduct(Long code,Integer quantity, Invoice invoice);

    void updateStatusToClosed(Invoice invoice);

}
