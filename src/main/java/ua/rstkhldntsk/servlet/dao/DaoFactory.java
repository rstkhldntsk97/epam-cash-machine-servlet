package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.UserDAO;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDAO createUserDao();

    public abstract ProductDAO createProductDao();

    public abstract InvoiceDAO createInvoiceDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
