package ua.rstkhldntsk.servlet.model.dao;

import ua.rstkhldntsk.servlet.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDAO createUserDao();

    public abstract ProductDAO createProductDao();

    public abstract StockDAO createStockDao();

    public abstract RoleDAO createRoleDao();

    public abstract ReceiptDAO createReceiptDao();

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
