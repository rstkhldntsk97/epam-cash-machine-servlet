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
            invoice.setId(resultSet.getLong("invoice.id"));
            invoice.setStatus(resultSet.getString("invoice.status"));
            invoice.setCreatedAt(resultSet.getDate("invoice.created_at"));
            invoice.setUser(userMapper.extractFromResultSet(resultSet));
            Product product = productMapper.extractFromResultSet(resultSet);
            int quantityInInvoice = resultSet.getInt("quantity_in_invoice");
            invoice.getProducts().put(product, quantityInInvoice);
            invoice.setTotal(resultSet.getFloat("invoice.total_price"));
//            invoice.setTotal(invoice.getProducts().values().stream().reduce(0, Integer::sum));

            return invoice;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
