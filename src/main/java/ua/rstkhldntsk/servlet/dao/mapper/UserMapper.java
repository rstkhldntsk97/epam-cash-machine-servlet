package ua.rstkhldntsk.servlet.dao.mapper;

import ua.rstkhldntsk.servlet.model.Role;
import ua.rstkhldntsk.servlet.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {

    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
        return user;
    }

}
