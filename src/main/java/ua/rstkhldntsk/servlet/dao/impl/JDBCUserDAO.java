package ua.rstkhldntsk.servlet.dao.impl;

import ua.rstkhldntsk.servlet.dao.UserDAO;
import ua.rstkhldntsk.servlet.model.Role;
import ua.rstkhldntsk.servlet.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.*;

public class JDBCUserDAO implements UserDAO {

    private DataSource dataSource;

    public JDBCUserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
//            UserMapper userMapper = new UserMapper();
//            User user = userMapper.extractFromResultSet(resultSet);
            User user = new User();
            return Optional.of(user);
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void create(User user) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(generatedKeys);
            close(preparedStatement);
            close(con);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getString("role"));
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        } finally {
            close(resultSet);
            close(statement);
            close(con);
        }
        return users;
    }

    @Override
    public Optional<User> findByUsername(String name) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            statement = con.prepareStatement(FIND_BY_USERNAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                Role role = Role.valueOf(resultSet.getString("role"));
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        } finally {
            close(resultSet);
            close(statement);
            close(con);
        }
    }

    @Override
    public Role getUserRole(User user) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            statement = con.prepareStatement(FIND_USER_ROLE_BY_ID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Role.valueOf(resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        } finally {
            close(resultSet);
            close(statement);
            close(con);
        }
        return null;
    }


    public boolean checkUser(String username, String password) {
        boolean exist = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_CHECK_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();
            exist = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(statement);
            close(connection);
        }
        return exist;
    }
}
