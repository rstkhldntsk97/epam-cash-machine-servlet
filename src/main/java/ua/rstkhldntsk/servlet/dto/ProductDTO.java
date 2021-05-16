package ua.rstkhldntsk.servlet.dto;

import java.math.BigDecimal;

public class ProductDTO {

    private String name;
    private Integer code;
    private BigDecimal price;
    private Integer quantity;

    public ProductDTO(String name, Integer code, BigDecimal price, Integer quantity) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
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
}
