package ua.rstkhldntsk.servlet.services;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.UserDAO;
import ua.rstkhldntsk.servlet.exceptions.InvalidInput;
import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.exceptions.UserNotExist;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.utils.Encoder;
import ua.rstkhldntsk.servlet.utils.Page;
import ua.rstkhldntsk.servlet.utils.Validator;

import java.util.List;
import java.util.Optional;

public class UserService {

    private static volatile UserService instance;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final UserDAO userDAO = daoFactory.createUserDao();

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

    public Page<User> findAllByPage(Integer pageInfo) {
        Page<User> page = new Page<>();
        page.setContent(userDAO.findAllByPage(pageInfo));
        int totalRecords = userDAO.findAll().size();
        page.setTotalRecords(totalRecords);
        int totalPages = (int) Math.ceil(page.getTotalRecords() * 1.0 / page.getMaxResult());
        page.setTotalPages(totalPages);
        return page;
    }

    public void create(String name, String pass, String role) throws InvalidInput {
        String username = Validator.usernameValidate(name);
        String password = Validator.passwordValidate(pass);
        userDAO.create(new User(username, password, role));
    }

    public User login(String name, String pass) throws UserNotExist {
        String username = null;
        String password = null;
        try {
            username = Validator.usernameValidate(name);
            password = Validator.passwordValidate(pass);
            String passwordEncrypted = Encoder.encodePassword(password);
            Optional<User> user = userDAO.findByName(username, passwordEncrypted);
            if (user.isPresent()) {
                return user.get();
            }
            throw new UserNotExist();
        } catch (InvalidInput invalidInput) {
            throw new UserNotExist();
        }
    }
}
