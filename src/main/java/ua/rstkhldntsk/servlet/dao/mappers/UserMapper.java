package ua.rstkhldntsk.servlet.dao.mappers;

import ua.rstkhldntsk.servlet.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper  implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            User user = new User();
            Long id = resultSet.getLong("user.id");
            String password = resultSet.getString("user.password");
            String username = resultSet.getString("user.username");
            String role = resultSet.getString("user.role");
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
