package ua.rstkhldntsk.servlet.model.dao.impl;

import ua.rstkhldntsk.servlet.model.dao.UserDAO;
import ua.rstkhldntsk.servlet.model.dao.mapper.UserMapper;
import ua.rstkhldntsk.servlet.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCUserDAO implements UserDAO {
    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_USERNAME)) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            UserMapper userMapper = new UserMapper();
            User user = userMapper.extractFromResultSet(resultSet);

            return Optional.of(user);

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
