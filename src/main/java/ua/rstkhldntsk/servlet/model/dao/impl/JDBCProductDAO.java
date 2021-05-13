package ua.rstkhldntsk.servlet.model.dao.impl;

import ua.rstkhldntsk.servlet.model.dao.ProductDAO;
import ua.rstkhldntsk.servlet.model.dao.mapper.ProductMapper;
import ua.rstkhldntsk.servlet.model.entity.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void save(Product product) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getCode());
            preparedStatement.setBigDecimal(3 , product.getPrice());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
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
        return null;
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
}
