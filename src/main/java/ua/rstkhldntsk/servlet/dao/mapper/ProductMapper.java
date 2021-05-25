package ua.rstkhldntsk.servlet.dao.mapper;

import ua.rstkhldntsk.servlet.model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ObjectMapper<Product> {
    @Override
    public Product extractFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Long code = resultSet.getLong("products.code");
            String name = resultSet.getString("products.name");
            BigDecimal price = resultSet.getBigDecimal("products.price");
            Integer quantity = resultSet.getInt("products.quantity");

            return new Product(code , name, price, quantity);
        }
        return new Product();
    }
}
