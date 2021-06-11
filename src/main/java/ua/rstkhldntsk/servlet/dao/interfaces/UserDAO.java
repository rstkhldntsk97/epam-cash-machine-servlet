package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    /**
     * finds user by username and password in database
     *
     * @param name user username
     * @param pass user password
     * @return optional of user
     */
    Optional<User> findByName(String name, String pass);

    List<User> findAllByPage(Integer pageInfo);

    /**
     * finds user by id in database
     *
     * @param id user id
     * @return optional of user
     */
    Optional<User> findById(Long id);

}
