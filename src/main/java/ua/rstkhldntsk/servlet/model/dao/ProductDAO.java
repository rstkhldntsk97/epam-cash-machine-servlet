package ua.rstkhldntsk.servlet.model.dao;

import ua.rstkhldntsk.servlet.model.entity.Product;

import java.util.Optional;

public interface ProductDAO extends GenericDAO<Product>{

    Optional<Product> findByName(String name);

}
