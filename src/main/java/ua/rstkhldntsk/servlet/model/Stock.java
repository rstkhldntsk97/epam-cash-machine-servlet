package ua.rstkhldntsk.servlet.model;

import java.util.List;

public class Stock {

    List<Product> stock;

    public Stock(List<Product> stock) {
        this.stock = stock;
    }

    public Stock() {
    }

    public List<Product> getStock() {
        return stock;
    }

    public void setStock(List<Product> stock) {
        this.stock = stock;
    }
}
