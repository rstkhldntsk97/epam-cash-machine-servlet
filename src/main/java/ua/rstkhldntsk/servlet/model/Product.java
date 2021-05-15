package ua.rstkhldntsk.servlet.model;

import java.math.BigDecimal;

public class Product {

    private Integer id;
    private String name;
    private Integer code;
    private BigDecimal price;
    private Integer quantity;

    public Product(Integer id, String name, Integer code, BigDecimal price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
