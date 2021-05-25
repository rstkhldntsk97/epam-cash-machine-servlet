package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.model.Product;

import java.util.Optional;

public interface ProductDAO extends GenericDAO<Product>{

    Optional<Product> findByName(String name);


    Optional<Product> findByCode(Long code);

}
