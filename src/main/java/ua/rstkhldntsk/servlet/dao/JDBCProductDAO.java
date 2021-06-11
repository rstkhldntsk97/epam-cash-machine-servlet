package ua.rstkhldntsk.servlet.dao;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.dao.mappers.ProductMapper;
import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.*;

public class JDBCProductDAO implements ProductDAO {

    private static final Logger LOGGER = Logger.getLogger(JDBCProductDAO.class);
    private DataSource dataSource;

    public JDBCProductDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Product> findById(Integer code, Integer langId) {
        Optional<Product> result = Optional.empty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_CODE);
            preparedStatement.setInt(1, langId);
            preparedStatement.setInt(2, code);
            resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            if (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return result;
    }

    @Override
    public boolean create(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBigDecimal(1, product.getPrice());
            preparedStatement.setInt(2, product.getQuantity());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setCode(generatedKeys.getInt(1));
            }
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

    public boolean createTranslate(Product product, String translateUA) throws ProductAlreadyExistException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PRODUCT_EN);
            preparedStatement.setLong(1, product.getCode());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setLong(3, product.getCode());
            preparedStatement.setString(4, translateUA);
            return preparedStatement.executeUpdate() == 2;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new ProductAlreadyExistException();
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }


    @Override
    public boolean update(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setInt(1, product.getQuantity());
            preparedStatement.setLong(2, product.getCode());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        } finally {
            close(preparedStatement);
            close(connection);
        }
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
                products.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return products;
    }


    public List<Product> findAllByPage(Integer page, Integer langId) {
        if (langId == null) {
            langId = 1;
        }
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_PRODUCTS_BY_LANG + " order by code ASC LIMIT " + (page - 1) * 5 + "," + 5);
            preparedStatement.setInt(1, langId);
            resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                products.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return products;
    }

    @Override
    public Optional<Product> findByName(String name, Integer langId) {
        Optional<Product> result = Optional.empty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_NAME);
            preparedStatement.setInt(1, langId);
            preparedStatement.setString(2, name);
            resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            if (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return result;
    }

}
