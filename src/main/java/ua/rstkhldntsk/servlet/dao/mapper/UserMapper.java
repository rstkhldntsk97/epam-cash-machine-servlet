package ua.rstkhldntsk.servlet.dao.mapper;

import ua.rstkhldntsk.servlet.model.Role;
import ua.rstkhldntsk.servlet.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {

    public User extractFromResultSet(ResultSet resultSet) throws SQLException {

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role_name");

            return new User(id , username, password, Role.valueOf(role));
        }
        return new User();
    }

}
