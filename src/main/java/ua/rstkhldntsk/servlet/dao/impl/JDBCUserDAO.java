package ua.rstkhldntsk.servlet.dao.impl;

import ua.rstkhldntsk.servlet.dao.UserDAO;
import ua.rstkhldntsk.servlet.dao.mapper.UserMapper;
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
    public Optional<User> findById(Integer id) {
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            User user = userMapper.extractFromResultSet(resultSet);
            return Optional.of(user);
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = User.createUser(username, password);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
        return users;
    }

    @Override
    public Optional<User> findByUsername(String name) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(FIND_BY_USERNAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            User user = userMapper.extractFromResultSet(resultSet);
            return Optional.of(user);
        } catch (SQLException e) {
            throw new IllegalStateException();
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
                return Role.valueOf(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
        finally {
            try {
                resultSet.close();
                statement.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
