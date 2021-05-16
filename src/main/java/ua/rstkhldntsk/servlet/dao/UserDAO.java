package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User>{

    public Optional<User> findByUsername(String name);

    public List<User> findAll();

}
