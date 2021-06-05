package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByName(String name);
    /**
     * finds user by username and password in database
     *
     * @param username user username
     * @param password user password
     * @return true if user exist false if not
     */
    boolean checkUser(String username, String password);

    List<User> findAllByPage(Integer pageInfo);

    /**
     * finds user by id in database
     *
     * @param id user id
     * @return optional of user
     */
    Optional<User> findById(Long id);

}
