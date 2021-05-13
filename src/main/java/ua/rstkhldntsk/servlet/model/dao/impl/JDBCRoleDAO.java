package ua.rstkhldntsk.servlet.model.dao.impl;

import ua.rstkhldntsk.servlet.model.dao.RoleDAO;
import ua.rstkhldntsk.servlet.model.entity.ERole;

import java.util.List;
import java.util.Optional;

public class JDBCRoleDAO implements RoleDAO {
    @Override
    public Optional<ERole> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(ERole model) {

    }

    @Override
    public void update(ERole model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<ERole> findAll() {
        return null;
    }

    @Override
    public void setUserRole() {

    }

    @Override
    public void deleteUserRole() {

    }
}
