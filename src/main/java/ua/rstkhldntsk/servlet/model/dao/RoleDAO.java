package ua.rstkhldntsk.servlet.model.dao;

import ua.rstkhldntsk.servlet.model.entity.ERole;

public interface RoleDAO extends GenericDAO<ERole>{

    void setUserRole();

    void deleteUserRole();

}
