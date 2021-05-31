package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.dao.interfaces.UserDAO;
import ua.rstkhldntsk.servlet.dao.mappers.UserMapper;
import ua.rstkhldntsk.servlet.exceptions.ItemExistException;
import ua.rstkhldntsk.servlet.models.User;

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
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            statement = con.prepareStatement(FIND_BY_ID);
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
    public void create(User user) throws ItemExistException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new ItemExistException();
        } finally {
            close(generatedKeys);
            close(preparedStatement);
            close(con);
        }
    }

    @Override
    public boolean update(User user) {
        return false;
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
            throw new IllegalStateException();
        } finally {
            close(resultSet);
            close(statement);
            close(con);
        }
        return users;
    }

    @Override
    public Optional<User> findByName(String name) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            statement = con.prepareStatement(FIND_BY_USERNAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            UserMapper mapper = new UserMapper();
            if (resultSet.next()) {
                return Optional.of(mapper.extractFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        } finally {
            close(resultSet);
            close(statement);
            close(con);
        }
    }

    @Override
    public String getUserRole(User user) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            statement = con.prepareStatement(FIND_USER_ROLE_BY_ID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role");
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
