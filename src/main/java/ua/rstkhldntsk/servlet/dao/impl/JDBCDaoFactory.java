package ua.rstkhldntsk.servlet.dao.impl;

import ua.rstkhldntsk.servlet.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    /**
     * creates connection with database
     *
     * @return connection
     */
    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDAO createUserDao() {
        return new JDBCUserDAO(dataSource);
    }

    @Override
    public ProductDAO createProductDao() {
        return new JDBCProductDAO(dataSource);
    }

    @Override
    public InvoiceDAO createInvoiceDao() {
        return new JDBCInvoiceDAO(dataSource);
    }

    @Override
    public InvoiceProductDAO createInvoiceProductDao() {
        return new JDBCInvoiceProductDAO(dataSource);
    }
}
