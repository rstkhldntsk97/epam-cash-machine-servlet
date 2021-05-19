package ua.rstkhldntsk.servlet.dao.impl;

import ua.rstkhldntsk.servlet.dao.ReceiptDAO;
import ua.rstkhldntsk.servlet.model.Receipt;
import ua.rstkhldntsk.servlet.model.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JDBCReceiptDAO implements ReceiptDAO {

    private DataSource dataSource;

    public JDBCReceiptDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Receipt> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void create(Receipt model) {

    }

    @Override
    public void update(Receipt model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }

    @Override
    public List<Receipt> findAllByUser(User user) {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
