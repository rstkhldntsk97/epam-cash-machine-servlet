package ua.rstkhldntsk.servlet.dao.mappers;

import ua.rstkhldntsk.servlet.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper  implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            User user = new User();
            Long id = resultSet.getLong("id");
            String password = resultSet.getString("password");
            String username = resultSet.getString("username");
            String role = resultSet.getString("role");
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
