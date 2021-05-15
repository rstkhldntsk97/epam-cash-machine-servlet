package ua.rstkhldntsk.servlet.dao.mapper;

import ua.rstkhldntsk.servlet.model.Receipt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptMapper implements ObjectMapper<Receipt> {
    @Override
    public Receipt extractFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
