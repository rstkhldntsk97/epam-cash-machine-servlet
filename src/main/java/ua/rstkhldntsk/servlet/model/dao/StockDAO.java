package ua.rstkhldntsk.servlet.model.dao;

import ua.rstkhldntsk.servlet.model.entity.Product;
import ua.rstkhldntsk.servlet.model.entity.Stock;

import java.util.Optional;

public interface StockDAO extends GenericDAO<Stock>{

    Optional<Stock> findByProduct(Product product);

}
