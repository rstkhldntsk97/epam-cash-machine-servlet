package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.dao.mappers.ProductMapper;
import ua.rstkhldntsk.servlet.dao.mappers.UserMapper;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.*;

public class JDBCInvoiceDAO implements InvoiceDAO {

    private DataSource dataSource;

    public JDBCInvoiceDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Invoice invoice = new Invoice();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_INVOICE_BY_ID);
            resultSet = preparedStatement.executeQuery();
            ProductMapper productMapper = new ProductMapper();
            UserMapper userMapper = new UserMapper();
            while (resultSet.next()) {
                String status = resultSet.getString("status");
                Date createdAt = resultSet.getDate("create_at");
                Float productPrice = resultSet.getFloat("price");
                Integer quantityInInvoice = resultSet.getInt("quantity_in_invoice");
                User user = userMapper.extractFromResultSet(resultSet);

                invoice.setId(id);
                invoice.setUser(user);
                invoice.setCreatedAt(createdAt);
                invoice.setStatus(status);
                invoice.setTotal(productPrice * quantityInInvoice);
                invoice.getProducts().put(productMapper.extractProductFromInvoiceResultSet(resultSet), resultSet.getInt("quantity_in_invoice"));
            }
            System.out.println(invoice);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(generatedKeys);
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public void update(Invoice invoice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_INVOICE_TOTAL);
            preparedStatement.setLong(1 , invoice.getId());
            preparedStatement.setLong(2 , invoice.getId());
        } catch (SQLException e) {

        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Invoice> findAll() {
        return null;
    }

    @Override
    public List<Invoice> findAllByUser(User user) {
        return null;
    }

    @Override
    public void addProduct(Long code, Invoice invoice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(ADD_PRODUCT_TO_INVOICE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1 , invoice.getId());
            preparedStatement.setLong(2, code);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(generatedKeys);
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public void updateStatusToClosed(Invoice invoice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_INVOICE_STATUS);
            preparedStatement.setString(1, invoice.getStatus());
            preparedStatement.setLong(1 , invoice.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

}
