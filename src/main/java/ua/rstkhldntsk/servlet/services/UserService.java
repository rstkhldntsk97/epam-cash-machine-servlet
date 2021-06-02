package ua.rstkhldntsk.servlet.services;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.UserDAO;
import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.utils.Page;

import java.util.List;
import java.util.Optional;

public class UserService {

    private static volatile UserService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    UserDAO userDAO = daoFactory.createUserDao();

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

    public Page<User> findAllByPage (Integer pageInfo) {
        Page<User> page = new Page<>();
        page.setContent(userDAO.findAllByPage(pageInfo));
        int totalRecords = userDAO.findAll().size();
        page.setTotalRecords(totalRecords);
        int totalPages = (int) Math.ceil(page.getTotalRecords() * 1.0 / page.getMaxResult());
        page.setTotalPages(totalPages);
        return page;
    }

    public void create(User user) {
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
