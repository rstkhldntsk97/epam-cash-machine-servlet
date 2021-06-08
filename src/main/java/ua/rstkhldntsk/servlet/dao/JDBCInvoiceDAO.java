package ua.rstkhldntsk.servlet.dao;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.mappers.InvoiceMapper;
import ua.rstkhldntsk.servlet.dao.mappers.UserMapper;
import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.*;

public class JDBCInvoiceDAO implements InvoiceDAO {

    private static final Logger LOGGER = Logger.getLogger(JDBCInvoiceDAO.class);

    private final DataSource dataSource;

    public JDBCInvoiceDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Invoice> findById(Integer id, Integer langId) {
        Optional<Invoice> invoice = Optional.empty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_INVOICE_BY_ID);
            preparedStatement.setInt(1, langId);
            preparedStatement.setInt(2, id);
            resultSet = preparedStatement.executeQuery();
            InvoiceMapper invoiceMapper = new InvoiceMapper();
            invoice = Optional.of(invoiceMapper.extractFromResultSet(resultSet));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return invoice;
    }

    @Override
    public boolean create(Invoice invoice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_INVOICE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, invoice.getUser().getId());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getInt(1));
            }
            invoice.setStatus("NEW");
            LOGGER.debug("Invoice " + invoice.getId() + " created successfully");
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        } finally {
            close(generatedKeys);
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public boolean update(Invoice invoice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_INVOICE);
            preparedStatement.setLong(1, invoice.getId());
            preparedStatement.setString(2, invoice.getStatus());
            preparedStatement.setLong(3, invoice.getId());
            if (preparedStatement.executeUpdate() != 1) {
                LOGGER.error("Something went wrong in updating invoice");
                return false;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean delete(Invoice invoice) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = dataSource.getConnection();
            stmt = con.prepareStatement(DELETE_INVOICE);
            stmt.setLong(1, invoice.getId());
            if (stmt.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        } finally {
            close(stmt);
            close(con);
        }
        return true;
    }

    @Override
    public List<Invoice> findAll() {
        List<Invoice> invoices = new ArrayList<>();
        UserMapper userMapper = new UserMapper();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(FIND_ALL_INVOICES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("id"));
                invoice.setTotal(resultSet.getFloat("total_price"));
                invoice.setUser(userMapper.extractFromResultSet(resultSet));
                invoice.setStatus(resultSet.getString("status"));
                invoice.setCreatedAt(resultSet.getDate("created_at"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(con);
        }
        return invoices;
    }

    @Override
    public List<Invoice> findAllByUser(User user) {
        List<Invoice> invoices = new ArrayList<>();
        UserMapper userMapper = new UserMapper();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(FIND_ALL_INVOICES_BY_USER);
            preparedStatement.setLong(1, user.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("id"));
                invoice.setTotal(resultSet.getFloat("total_price"));
                invoice.setUser(userMapper.extractFromResultSet(resultSet));
                invoice.setStatus(resultSet.getString("status"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(con);
        }
        return invoices;
    }

    @Override
    public void addProduct(Integer code, Integer quantity, Invoice invoice, BigDecimal price) throws ProductAlreadyExistException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(ADD_PRODUCT_TO_INVOICE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, invoice.getId());
            preparedStatement.setLong(2, code);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setBigDecimal(4, price);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.debug("can't add this product");
            throw new ProductAlreadyExistException();
        } finally {
            close(generatedKeys);
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public boolean deleteProductFromInvoice(Integer productCode, Integer invoiceId) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(DELETE_PRODUCT_FROM_INVOICE);
            preparedStatement.setInt(1, invoiceId);
            preparedStatement.setInt(2, productCode);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("can't delete product");
            return false;
        } finally {
            close(preparedStatement);
            close(con);
        }
    }

}
