package ua.rstkhldntsk.servlet.dao.mapper;

import ua.rstkhldntsk.servlet.model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ObjectMapper<Product> {
    @Override
    public Product extractFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("product_name");
            Integer code = resultSet.getInt("code");
            BigDecimal price = resultSet.getBigDecimal("price");
            Integer quantity = resultSet.getInt("quantity");

            return new Product(id , name, code, price, quantity);
        }
        return new Product();
    }
}
