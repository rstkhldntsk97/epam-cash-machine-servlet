package ua.rstkhldntsk.servlet.model;


/**
 * Represents an ProductInReceipt Entity
 */
public class ProductInReceipt {

    private Long id;
    private Product product;
    private Integer quantity;
    private Receipt receipt;

    public ProductInReceipt(Long id, Product product, Integer quantity, Receipt receipt) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.receipt = receipt;
    }

    public ProductInReceipt() {
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

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
