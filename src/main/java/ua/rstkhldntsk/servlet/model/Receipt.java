package ua.rstkhldntsk.servlet.model;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    private Integer id;
    private List<Product> productsInReceipt;
    private boolean isClosed;
    private BigDecimal total;

    public Receipt(Integer id, List<Product> productsInReceipt, boolean isClosed, BigDecimal total) {
        this.id = id;
        this.productsInReceipt = productsInReceipt;
        this.isClosed = isClosed;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProductsInReceipt() {
        return productsInReceipt;
    }

    public void setProductsInReceipt(List<Product> productsInReceipt) {
        this.productsInReceipt = productsInReceipt;
    }

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
        float totalPrice = 0;
        for (Product p: productsInReceipt) {
            totalPrice += p.getPrice().floatValue();
        }
        this.total = BigDecimal.valueOf(totalPrice);
    }
}
