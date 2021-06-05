package ua.rstkhldntsk.servlet.dao.mappers;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper implements ObjectMapper<Invoice> {

    ProductMapper productMapper = new ProductMapper();
    UserMapper userMapper = new UserMapper();

    @Override
    public Invoice extractFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            Invoice invoice = new Invoice();
            while (resultSet.next()) {
                invoice.setId(resultSet.getInt("id"));
                invoice.setStatus(resultSet.getString("status"));
                invoice.setCreatedAt(resultSet.getDate("created_at"));
                invoice.setUser(userMapper.extractFromResultSet(resultSet));
                Product product = productMapper.extractFromResultSet(resultSet);
                int quantityInInvoice = resultSet.getInt("quantity_in_invoice");
                invoice.getProducts().put(product, quantityInInvoice);
                invoice.setTotal(resultSet.getFloat("total_price"));
            }
            return invoice;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
