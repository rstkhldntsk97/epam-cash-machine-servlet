package ua.rstkhldntsk.servlet.model.entity;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    private Integer id;
    private List<Product> sailedProducts;
    private BigDecimal total;

    //constructors
    public Receipt(Integer id, List<Product> sailedProducts, BigDecimal total) {
        this.id = id;
        this.sailedProducts = sailedProducts;
        this.total = total;
    }

    public Receipt(Integer id, BigDecimal total) {
        this.id = id;
        this.total = total;
    }

    public Receipt(Integer id) {
        this.id = id;
    }

    public Receipt() {
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getSailedProducts() {
        return sailedProducts;
    }

    public void setSailedProducts(List<Product> sailedProducts) {
        this.sailedProducts = sailedProducts;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        float totalPrice = 0;
        for (Product p:sailedProducts) {
            totalPrice += p.getPrice().floatValue();
        }
        this.total = BigDecimal.valueOf(totalPrice);
    }
}
