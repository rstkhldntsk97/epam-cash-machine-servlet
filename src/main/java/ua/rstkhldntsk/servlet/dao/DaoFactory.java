package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDAO createUserDao();

    public abstract ProductDAO createProductDao();

    public abstract InvoiceDAO createInvoiceDao();

    public abstract InvoiceProductDAO createInvoiceProductDao();


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
