package ua.rstkhldntsk.servlet.dao;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.mappers.InvoiceMapper;
import ua.rstkhldntsk.servlet.dao.mappers.UserMapper;
import ua.rstkhldntsk.servlet.exceptions.ItemExistException;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.models.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.*;

public class JDBCInvoiceDAO implements InvoiceDAO {

    private static final Logger LOGGER = Logger.getLogger(JDBCInvoiceDAO.class);

    private DataSource dataSource;

    public JDBCInvoiceDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_INVOICE_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            InvoiceMapper invoiceMapper = new InvoiceMapper();
            Invoice invoice = invoiceMapper.extractFromResultSet(resultSet);
            return Optional.of(invoice);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public void create(Invoice invoice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEW_INVOICE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1 , invoice.getUser().getId());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getLong(1));
            }
            LOGGER.debug("Invoice " + invoice.getId() + " created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
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
                LOGGER.debug("Something went wrong in updating invoice");
                return false;
            }
        } catch (SQLException e) {
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
        } catch (Exception e) {
            LOGGER.error("Can't delete invoice:" + invoice.getId());
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
                invoice.setId(resultSet.getLong("id"));
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
            preparedStatement.setLong(1 , user.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
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
    public void addProduct(Long code, Integer quantity, Invoice invoice, BigDecimal price) throws ItemExistException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(ADD_PRODUCT_TO_INVOICE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1 , invoice.getId());
            preparedStatement.setLong(2, code);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setBigDecimal(4, price);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.debug("can't add this product");
            throw new ItemExistException();
        } finally {
            close(generatedKeys);
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public Invoice findByUserAndInvoiceId(User user, Invoice invoice) {
        Invoice invoice1 = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(FIND_INVOICE_BY_USER_ID_AND_INVOICE_ID);
            preparedStatement.setLong(1 , user.getId());
            preparedStatement.setLong(1 , invoice.getId());
            resultSet = preparedStatement.executeQuery();
            InvoiceMapper mapper = new InvoiceMapper();
            while (resultSet.next()) {
                invoice1 = mapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(con);
        }
        return invoice1;
    }

}
