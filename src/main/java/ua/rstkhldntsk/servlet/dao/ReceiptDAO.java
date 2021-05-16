package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.model.Receipt;
import ua.rstkhldntsk.servlet.model.User;

import java.util.List;

public interface ReceiptDAO extends GenericDAO<Receipt> {

    /**
     * finds all users receipts
     *
     * @param user user
     * @return list of users receipts
     */
    List<Receipt> findAllByUser(User user);

}
