package ua.rstkhldntsk.servlet.dao;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.interfaces.UserDAO;
import ua.rstkhldntsk.servlet.dao.mappers.UserMapper;
import ua.rstkhldntsk.servlet.exceptions.UserExistException;
import ua.rstkhldntsk.servlet.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.*;

public class JDBCUserDAO implements UserDAO {

    private DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);
    public JDBCUserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            statement = con.prepareStatement(FIND_USER_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            UserMapper mapper = new UserMapper();
            if (resultSet.next()) {
                return Optional.of(mapper.extractFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException();
        } finally {
            close(resultSet);
            close(statement);
            close(con);
        }
    }

    @Override
    public boolean create(User user) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            if (preparedStatement.executeUpdate() !=1) {
                return false;
            }
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new UserExistException();
        } finally {
            close(generatedKeys);
            close(preparedStatement);
            close(con);
        }
    }

    @Override
    public boolean update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException();
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
            UserMapper mapper = new UserMapper();
            while (resultSet.next()) {
                users.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            close(resultSet);
            close(statement);
            close(con);
        }
        return users;
    }

    @Override
    public List<User> findAllByPage(Integer page) {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_USERS + " order by id ASC LIMIT " + (page - 1) * 5 + "," + 5);
            resultSet = preparedStatement.executeQuery();
            UserMapper mapper = new UserMapper();
            while (resultSet.next()) {
                users.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return users;
    }

    @Override
    public Optional<User> findByName(String name, String password) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            statement = con.prepareStatement(FIND_BY_USERNAME);
            statement.setString(1, name);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            UserMapper mapper = new UserMapper();
            if (resultSet.next()) {
                return Optional.of(mapper.extractFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();
        } finally {
            close(resultSet);
            close(statement);
            close(con);
        }
    }
}
