package ua.rstkhldntsk.servlet.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {

    Optional<T> findById(Integer id);

    void save(T model);

    void update(T model);

    void delete(Integer id);

    List<T> findAll();

}
