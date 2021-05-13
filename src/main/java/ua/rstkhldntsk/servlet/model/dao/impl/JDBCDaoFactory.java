package ua.rstkhldntsk.servlet.model.dao.impl;

import ua.rstkhldntsk.servlet.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    @Override
    public UserDAO createUserDao() {
        return null;
    }

    @Override
    public ProductDAO createProductDao() {
        return null;
    }

    @Override
    public StockDAO createStockDao() {
        return null;
    }

    @Override
    public RoleDAO createRoleDao() {
        return null;
    }

    @Override
    public ReceiptDAO createReceiptDao() {
        return null;
    }
}
