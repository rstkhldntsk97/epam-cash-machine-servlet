package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.UserDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

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

    @Override
    public InvoiceDAO createInvoiceDao() {
        return new JDBCInvoiceDAO(dataSource);
    }

}
