package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.model.Invoice;
import ua.rstkhldntsk.servlet.model.User;

import java.util.List;

public interface InvoiceDAO extends GenericDAO<Invoice> {

    /**
     * finds all users receipts
     *
     * @param user user
     * @return list of users receipts
     */
    List<Invoice> findAllByUser(User user);

}
