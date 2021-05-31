package ua.rstkhldntsk.servlet.dao;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.dao.mappers.ProductMapper;
import ua.rstkhldntsk.servlet.exceptions.ItemExistException;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.services.InvoiceService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.*;

public class JDBCProductDAO implements ProductDAO {

    private DataSource dataSource;

    public JDBCProductDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Product> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Product product) throws ItemExistException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setCode(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new ItemExistException();
        } finally {
            close(generatedKeys);
            close(preparedStatement);
            close(connection);
        }
    }


    @Override
    public boolean update(Product model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Product product) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_PRODUCTS);
            resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
//                Long code = resultSet.getLong("code");
//                String name = resultSet.getString("name");
//                BigDecimal price = resultSet.getBigDecimal("price");
//                Integer quantity = resultSet.getInt("quantity");
//                products.add(new Product(code, name, price, quantity));
                products.add(mapper.extractFromResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public Optional<Product> findByName(String name) {
        Optional<Product> result = Optional.empty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            if (resultSet.next()) {
//                Long id = resultSet.getLong("code");
//                BigDecimal price = resultSet.getBigDecimal("price");
//                Integer quantity = resultSet.getInt("quantity");
//                Product product = new Product(id, name, price, quantity);
//                result = Optional.of(product);
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {

        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return result;
    }

    @Override
    public Optional<Product> findByCode(Long code) {
        Optional<Product> result = Optional.empty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_CODE);
            preparedStatement.setLong(1, code);
            resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            if (resultSet.next()) {
//                String name = resultSet.getString("name");
//                BigDecimal price = resultSet.getBigDecimal("price");
//                Integer quantity = resultSet.getInt("quantity");
//                Product product = new Product(code, name, price, quantity);
//                result = Optional.of(product);
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {

        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return result;
    }

}
