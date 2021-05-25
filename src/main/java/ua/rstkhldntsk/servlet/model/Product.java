package ua.rstkhldntsk.servlet.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents an Product Entity
 */
public class Product {

    private Long code;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public Product(Long code, String name, BigDecimal price, Integer quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }

    // getters and setters
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return code.equals(product.code) &&
                name.equals(product.name) &&
                price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + code +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
