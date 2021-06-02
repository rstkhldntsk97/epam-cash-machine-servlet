package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends GenericDAO<Product> {

    Optional<Product> findByName(String name);


    Optional<Product> findByCode(Long code);

    List<Product> findAllByPage(Integer page, String lang);

    public void createTranslateEN(Product product, String translate) throws ProductAlreadyExistException;

    public void createTranslateUA(Product product, String translate) throws ProductAlreadyExistException;

}
