package ua.rstkhldntsk.servlet.dao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.database.DBInitializer;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;

import java.util.Optional;

import static org.junit.Assert.*;

public class JDBCInvoiceDAOTest {

    private InvoiceDAO invoiceDAO;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        invoiceDAO = daoFactory.createInvoiceDao();
    }

    @Test
    public void findByIdNotEquals(){
        Invoice invoice = invoiceDAO.findById(1L, 1).get();
        Invoice invoice1 = invoiceDAO.findById(12L, 1).get();
        assertNotEquals(invoice, invoice1);
    }

    @Test
    public void create() {
        User user = DaoFactory.getInstance().createUserDao().findByName("cashier1").get();
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        assertTrue(invoiceDAO.create(invoice));
    }

    @Test (expected = NullPointerException.class)
    public void createFailedWithException() {
        invoiceDAO.create(new Invoice());
    }

    @Test
    public void createFailed() {
        User user = new User();
        user.setId(-1L);
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoiceDAO.create(invoice);
    }

}
