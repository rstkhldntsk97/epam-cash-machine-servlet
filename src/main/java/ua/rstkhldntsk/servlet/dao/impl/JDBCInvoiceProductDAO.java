package ua.rstkhldntsk.servlet.dao.impl;

import ua.rstkhldntsk.servlet.dao.InvoiceProductDAO;
import ua.rstkhldntsk.servlet.dao.mapper.ProductMapper;
import ua.rstkhldntsk.servlet.model.Invoice;
import ua.rstkhldntsk.servlet.model.InvoiceProduct;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.SELECT_PRODUCTS_BY_INVOICE;

public class JDBCInvoiceProductDAO implements InvoiceProductDAO {

    private DataSource dataSource;

    public JDBCInvoiceProductDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<InvoiceProduct> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void create(InvoiceProduct invoiceProduct) {

    }

    @Override
    public void update(InvoiceProduct invoiceProduct) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<InvoiceProduct> findAll() {
        return null;
    }

    public Set<InvoiceProduct> findAllByInvoice(Invoice invoice) {
        Set<InvoiceProduct> result = new HashSet<>();
        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(SELECT_PRODUCTS_BY_INVOICE)) {
            ps.setLong(1, invoice.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InvoiceProduct invoiceProduct = new InvoiceProduct();
                ProductMapper productMapper = new ProductMapper();

                invoiceProduct.setId(rs.getLong("invoice_products.id"));
                invoiceProduct.setPrice(rs.getBigDecimal("invoice_products.price"));
                invoiceProduct.setQuantity(rs.getInt("quantity_in_invoice"));
                invoiceProduct.setProduct(productMapper.extractFromResultSet(rs));
            }

        } catch (SQLException e) {

        }
        return result;
    }
}
