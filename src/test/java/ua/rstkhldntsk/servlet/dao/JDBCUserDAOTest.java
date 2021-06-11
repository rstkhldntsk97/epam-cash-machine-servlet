package ua.rstkhldntsk.servlet.dao;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.rstkhldntsk.servlet.dao.interfaces.UserDAO;
import ua.rstkhldntsk.servlet.database.DBInitializer;
import ua.rstkhldntsk.servlet.exceptions.UserExistException;
import ua.rstkhldntsk.servlet.models.User;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class JDBCUserDAOTest {

    private UserDAO userDao;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.createUserDao();
    }

    @Test
    public void findById() {
        Optional<User> user = userDao.findById(1L);
        assertTrue(user.isPresent());
    }

    @Test
    public void findByIdFail() {
        Optional<User> user = userDao.findById(22L);
        assertFalse(user.isPresent());
    }

    @Test
    public void create() {
        User user = getUser();
        userDao.create(user);
        assertNotNull(user.getId());
        userDao.delete(user);
    }

    @Test(expected = UserExistException.class)
    public void createFail() {
        userDao.create(new User("admin", "5f4dcc3b5aa765d61d8327deb882cf99", "ADMIN"));
    }

    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        assertNotNull(users);
    }

    @Test
    public void findAllByPage() {
        List<User> users = userDao.findAllByPage(1);
        assertNotNull(users);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() {
        userDao.update(getUser());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() {
        userDao.delete(getUser());
    }

    @Test
    public void findByNameEmpty() {
        assertEquals(Optional.empty(), userDao.findByName("test12", "123"));
    }

    @Test
    public void findByName() {
        userDao.findByName(getUser().getUsername(), getUser().getPassword());
    }

    private User getUser() {
        return new User("test", "test", "CASHIER");
    }

}
