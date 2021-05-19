package ua.rstkhldntsk.servlet.dao.impl;

import ua.rstkhldntsk.servlet.dao.ProductDAO;
import ua.rstkhldntsk.servlet.dao.mapper.ProductMapper;
import ua.rstkhldntsk.servlet.model.Product;

import javax.sql.DataSource;
import java.math.BigDecimal;
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
    public Optional<Product> findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Product product) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getCode());
            preparedStatement.setBigDecimal(3 , product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Product model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Product> findAll() {
       List<Product> products = new ArrayList<>();
        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(FIND_ALL_PRODUCTS)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Integer code = resultSet.getInt("code");
                String name = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("price");
                Integer quantity = resultSet.getInt("quantity");
                products.add(new Product(id, name, code, price, quantity));
            }
            return products;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Product> findByName(String name) {
        Optional<Product> result = Optional.empty();
        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(FIND_PRODUCT_BY_NAME)) {
            ps.setString(1, name);
            final ResultSet resultSet = ps.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {

        }
        return result;
    }

    @Override
    public Optional<Product> findByCode(Integer code) {
        Optional<Product> result = Optional.empty();
        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(FIND_PRODUCT_BY_CODE)) {
            ps.setInt(1, code);
            final ResultSet resultSet = ps.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {

        }
        return result;
    }

    @Override
    public void close() throws Exception {

    }
}
