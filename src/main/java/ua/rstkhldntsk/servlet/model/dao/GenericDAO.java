package ua.rstkhldntsk.servlet.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {

    Optional<T> findById(String id);

    void save(T model);

    void update(T model);

    void delete(Integer id);

    List<T> findAll();

}
