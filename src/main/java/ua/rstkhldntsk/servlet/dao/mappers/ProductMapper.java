package ua.rstkhldntsk.servlet.dao.mappers;

import ua.rstkhldntsk.servlet.models.Product;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ObjectMapper<Product> {
    @Override
    public Product extractFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            Product product = new Product();
            product.setCode(resultSet.getLong("product.code"));
            product.setName(resultSet.getString("product.name"));
            product.setPrice(resultSet.getBigDecimal("product.price"));
            product.setQuantity(resultSet.getInt("product.quantity"));
            return product;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
