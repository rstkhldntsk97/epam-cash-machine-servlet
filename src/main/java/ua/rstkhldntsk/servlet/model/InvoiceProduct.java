package ua.rstkhldntsk.servlet.model;


import java.math.BigDecimal;

/**
 * Represents an InvoiceProduct Entity
 */
public class InvoiceProduct {

    private Long id;
    private Product product;
    private Integer quantity;
    private Invoice invoice;
    private BigDecimal price;

    public InvoiceProduct(Long id, Product product, Integer quantity, Invoice invoice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.invoice = invoice;
    }

    public InvoiceProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
