package ua.rstkhldntsk.servlet.dao.mapper;

import ua.rstkhldntsk.servlet.model.ProductInReceipt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInReceiptMapper implements ObjectMapper<ProductInReceipt>{


    @Override
    public ProductInReceipt extractFromResultSet(ResultSet resultSet) throws SQLException {
        ProductInReceipt productInReceipt = new ProductInReceipt();
        ProductMapper productMapper = new ProductMapper();
        ReceiptMapper receiptMapper = new ReceiptMapper();
        if (resultSet.next()) {
            productInReceipt.setId(resultSet.getLong("id"));
            productInReceipt.setProduct(productMapper.extractFromResultSet(resultSet));
            productInReceipt.setQuantity(resultSet.getInt("quantity"));
            productInReceipt.setReceipt(receiptMapper.extractFromResultSet(resultSet));

            return productInReceipt;
        }
        return productInReceipt;
    }
}
