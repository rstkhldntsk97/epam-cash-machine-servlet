package ua.rstkhldntsk.servlet.dao.mapper;

import ua.rstkhldntsk.servlet.model.Receipt;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptMapper implements ObjectMapper<Receipt> {
    @Override
    public Receipt extractFromResultSet(ResultSet resultSet) throws SQLException {
        Receipt receipt = new Receipt();
        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Boolean isClosed = resultSet.getBoolean("is_closed");
            BigDecimal total = resultSet.getBigDecimal("total");
            receipt.setId(id);
            receipt.setClosed(isClosed);
            receipt.setTotal(total);

            return receipt;
        }
        return receipt;
    }
}
