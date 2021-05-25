package ua.rstkhldntsk.servlet.service;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.ProductDAO;
import ua.rstkhldntsk.servlet.dao.UserDAO;
import ua.rstkhldntsk.servlet.dao.impl.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.model.Product;
import ua.rstkhldntsk.servlet.model.User;

import java.util.List;

public class UserService {

    private static volatile UserService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    UserDAO userDAO = JDBCDaoFactory.getInstance().createUserDao();

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public void create(User user) {
        userDAO.create(user);
    }

    public String login(String username, String password) {
        if (userDAO.checkUser(username, password)) {
            return userDAO.findByUsername(username).get().getRole().toString();
        }
        return null;
    }
}
