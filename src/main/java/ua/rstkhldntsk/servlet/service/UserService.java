package ua.rstkhldntsk.servlet.service;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.UserDAO;
import ua.rstkhldntsk.servlet.model.User;

public class UserService {

    private UserDAO userDao = DaoFactory.getInstance().createUserDao();

    /**
     * saves new user
     *
     * @param user user
     * @return true if user created, false if not
     */
    public boolean addUser(User user) {
        userDao.create(user);
        return true;
    }

}
