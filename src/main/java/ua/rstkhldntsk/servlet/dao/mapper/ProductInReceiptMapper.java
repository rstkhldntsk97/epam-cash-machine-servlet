package ua.rstkhldntsk.servlet.dao.mapper;

import ua.rstkhldntsk.servlet.model.InvoiceProduct;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInReceiptMapper implements ObjectMapper<InvoiceProduct>{


    @Override
    public InvoiceProduct extractFromResultSet(ResultSet resultSet) throws SQLException {
        InvoiceProduct invoiceProduct = new InvoiceProduct();
        ProductMapper productMapper = new ProductMapper();
        ReceiptMapper receiptMapper = new ReceiptMapper();
        if (resultSet.next()) {
            invoiceProduct.setId(resultSet.getLong("id"));
            invoiceProduct.setProduct(productMapper.extractFromResultSet(resultSet));
            invoiceProduct.setQuantity(resultSet.getInt("quantity"));
            invoiceProduct.setInvoice(receiptMapper.extractFromResultSet(resultSet));

            return invoiceProduct;
        }
        return invoiceProduct;
    }
}
