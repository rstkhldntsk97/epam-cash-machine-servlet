package ua.rstkhldntsk.servlet.services;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.UserDAO;
import ua.rstkhldntsk.servlet.dao.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.models.User;

import java.util.List;

public class UserService {

    private static volatile UserService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    UserDAO userDAO = JDBCDaoFactory.getInstance().createUserDao();

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

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
