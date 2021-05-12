package ua.rstkhldntsk.servlet.model.entity;

import java.util.Set;


public class Stock {

    private Set<Product> stockList;

    public Set<Product> getStockList(){
        return stockList;
    }

}
