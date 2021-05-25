package ua.rstkhldntsk.servlet.dao.mapper;

import ua.rstkhldntsk.servlet.model.Invoice;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptMapper implements ObjectMapper<Invoice> {
    @Override
    public Invoice extractFromResultSet(ResultSet resultSet) throws SQLException {
        Invoice invoice = new Invoice();
        if (resultSet.next()) {
            Long id = resultSet.getLong("id");
            BigDecimal total = resultSet.getBigDecimal("total");
            invoice.setId(id);
            invoice.setTotal(total);

            return invoice;
        }
        return invoice;
    }
}
