package ua.rstkhldntsk.servlet.dao.impl;

import ua.rstkhldntsk.servlet.dao.InvoiceDAO;
import ua.rstkhldntsk.servlet.model.Invoice;
import ua.rstkhldntsk.servlet.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.INSERT_NEW_INVOICE;

public class JDBCInvoiceDAO implements InvoiceDAO {

    private DataSource dataSource;

    public JDBCInvoiceDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void create(Invoice invoice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEW_INVOICE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBigDecimal(1, invoice.getTotal());
            preparedStatement.setLong(2 , invoice.getUser().getId());
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
    public void update(Invoice model) {

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

}
