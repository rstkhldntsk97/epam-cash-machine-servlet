package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.model.Role;

public interface RoleDAO extends GenericDAO<Role>{

    void setUserRole();

    void deleteUserRole();

}
