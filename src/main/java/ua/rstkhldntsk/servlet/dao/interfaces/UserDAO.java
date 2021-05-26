package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.models.Role;
import ua.rstkhldntsk.servlet.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    public Optional<User> findByUsername(String name);

    public Role getUserRole(User user);

    public List<User> findAll();

    public boolean checkUser(String username, String password);

}
