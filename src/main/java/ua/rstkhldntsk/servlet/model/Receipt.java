package ua.rstkhldntsk.servlet.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Represents an Receipt Entity
 */
public class Receipt {

    private Integer id;
    private Map<Product, Integer> products;
    private boolean isClosed;
    private BigDecimal total;
    private User user;

    public Receipt(Map<Product, Integer> products, boolean isClosed, BigDecimal total) {
        this.products = products;
        this.isClosed = isClosed;
        this.total = total;
    }

    public Receipt() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

//    public void calculateTotalPrice() {
//        double sum = products.stream().mapToDouble(products -> products.getPrice().doubleValue()).sum();
//        this.total = BigDecimal.valueOf(sum);
//    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", products=" + products +
                ", isClosed=" + isClosed +
                ", total=" + total +
                '}';
    }

}
