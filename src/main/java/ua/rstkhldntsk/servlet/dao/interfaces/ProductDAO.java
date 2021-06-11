package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductDAO extends GenericDAO<Product> {

    /**
     * finds product by code
     *
     * @param id     product code
     * @param langId language of product name
     * @return optional of product
     */
    Optional<Product> findById(Integer id, Integer langId);

    /**
     * finds product by name
     *
     * @param name   product name
     * @param langId language of product name
     * @return optional of product
     */
    Optional<Product> findByName(String name, Integer langId);

    List<Product> findAllByPage(Integer page, Integer lang);

    boolean createTranslate(Product product,String translateUA) throws ProductAlreadyExistException;


}
