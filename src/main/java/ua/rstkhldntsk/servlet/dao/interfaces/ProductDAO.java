package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.models.Product;

import java.util.Optional;

public interface ProductDAO extends GenericDAO<Product> {

    Optional<Product> findByName(String name);


    Optional<Product> findByCode(Long code);

}
