package ua.rstkhldntsk.servlet.models;

import java.math.BigDecimal;
import java.util.*;

public class Invoice {

    private Long id;
    private Float total;
    private User user;
    private String status;
    private Date createdAt;
    private Map<Product, Integer> products = new HashMap<>();

    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", total=" + total +
                ", user=" + user.getId() + user.getUsername() +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", products=" + products +
                '}';
    }
}
