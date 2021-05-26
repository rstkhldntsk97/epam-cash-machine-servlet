package ua.rstkhldntsk.servlet.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an Invoice Entity
 */
public class Invoice {

    private Long id;
    private Set<InvoiceProduct> products = new HashSet<>();
    private BigDecimal total;
    private User user;
    private Status status;
    private Date createdAt;

    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void calculateTotalPrice() {
        double sum = products.stream().mapToDouble(products -> products.getPrice().doubleValue()).sum();
        this.total = BigDecimal.valueOf(sum);
    }

    public Set<InvoiceProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<InvoiceProduct> products) {
        this.products = products;
    }
}
