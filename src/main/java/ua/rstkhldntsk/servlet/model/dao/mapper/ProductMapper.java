package ua.rstkhldntsk.servlet.model.dao.mapper;

import ua.rstkhldntsk.servlet.model.entity.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ObjectMapper<Product> {
    @Override
    public Product extractFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("product_name");
            Integer code = resultSet.getInt("code");
            BigDecimal price = resultSet.getBigDecimal("price");

            return new Product(id , name, code, price);
        }
        return new Product();
    }
}
