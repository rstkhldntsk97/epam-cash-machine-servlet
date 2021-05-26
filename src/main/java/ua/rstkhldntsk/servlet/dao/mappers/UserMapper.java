package ua.rstkhldntsk.servlet.dao.mappers;

import ua.rstkhldntsk.servlet.models.Role;
import ua.rstkhldntsk.servlet.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper  implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        try {
//            Product product = new Product();
//            product.setCode(resultSet.getLong("products.code"));
//            product.setName(resultSet.getString("name"));
//            product.setPrice(resultSet.getBigDecimal("price"));
//            product.setQuantity(resultSet.getInt("quantity"));
//            return product;
            User user = new User();
            Long id = resultSet.getLong("user.id");
            String password = resultSet.getString("user.password");
            String username = resultSet.getString("user.username");
            Role role = Role.valueOf(resultSet.getString("user.role_name"));
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
