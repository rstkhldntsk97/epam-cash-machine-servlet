package ua.rstkhldntsk.servlet.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> extends AutoCloseable{

    Optional<T> findById(Integer id);

    void create(T model);

    void update(T model);

    void delete(Integer id);

    List<T> findAll();

}
