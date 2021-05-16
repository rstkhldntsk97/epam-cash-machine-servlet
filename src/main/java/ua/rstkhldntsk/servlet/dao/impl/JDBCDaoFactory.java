package ua.rstkhldntsk.servlet.dao.impl;

import ua.rstkhldntsk.servlet.dao.*;

import javax.sql.DataSource;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    @Override
    public UserDAO createUserDao() {
        return new JDBCUserDAO(dataSource);
    }

    @Override
    public ProductDAO createProductDao() {
        return new JDBCProductDAO(dataSource);
    }

//    @Override
//    public StockDAO createStockDao() {
//        return null;
//    }

    @Override
    public RoleDAO createRoleDao() {
        return null;
    }

    @Override
    public ReceiptDAO createReceiptDao() {
        return null;
    }
}
