package ua.rstkhldntsk.servlet.services;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.UserDAO;
import ua.rstkhldntsk.servlet.dao.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.exceptions.ItemExistException;
import ua.rstkhldntsk.servlet.models.User;

import java.util.List;
import java.util.Optional;

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

    public void create(User user) throws ItemExistException {
        userDAO.create(user);
    }

    public User login(String username, String password) {
        if (userDAO.checkUser(username, password)) {
            Optional<User> user = userDAO.findByName(username);
            if (user.isPresent()) {
                return user.get();
            }
        }
        return new User();
    }
}
