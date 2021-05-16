package ua.rstkhldntsk.servlet.dao.impl;

import ua.rstkhldntsk.servlet.dao.RoleDAO;
import ua.rstkhldntsk.servlet.model.Role;

import java.util.List;
import java.util.Optional;

public class JDBCRoleDAO implements RoleDAO {
    @Override
    public Optional<Role> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Role model) {

    }

    @Override
    public void update(Role model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public void setUserRole() {

    }

//    @Override
//    public void deleteUserRole() {
//
//    }
}
