package ua.rstkhldntsk.servlet.model.dao.mapper;

import ua.rstkhldntsk.servlet.model.dao.impl.JDBCUserDAO;
import ua.rstkhldntsk.servlet.model.entity.ERole;
import ua.rstkhldntsk.servlet.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends JDBCUserDAO {


    public User extractFromResultSet(ResultSet resultSet) throws SQLException {

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role_name");

            return new User(id , username, password, ERole.valueOf(role));
        }
        return new User();
    }

}
