package ua.rstkhldntsk.servlet.model.dao;

import ua.rstkhldntsk.servlet.model.entity.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User>{

    public Optional<User> findByUsername(String name);



}
