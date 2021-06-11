package ua.rstkhldntsk.servlet.dao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.rstkhldntsk.servlet.dao.interfaces.InvoiceDAO;
import ua.rstkhldntsk.servlet.database.DBInitializer;
import ua.rstkhldntsk.servlet.exceptions.InvalidInput;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.utils.Validator;

import java.util.List;
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
    public void findByIdNotEquals() {
        Invoice invoice = invoiceDAO.findById(1, 1).get();
        Invoice invoice1 = invoiceDAO.findById(12, 1).get();
        assertNotEquals(invoice, invoice1);
    }

//    @Test
//    public void create() {
//        User user = DaoFactory.getInstance().createUserDao().findByName("cashier1").get();
//        Invoice invoice = new Invoice();
//        invoice.setUser(user);
//        assertTrue(invoiceDAO.create(invoice));
//    }

    @Test(expected = NullPointerException.class)
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

    @Test
    public void findAll() {
        List<Invoice> invoiceDAOAll = invoiceDAO.findAll();
        assertNotNull(invoiceDAOAll);
    }

    @Test
    public void findAllByUser() {
        User user = new User();
        user.setId(1L);
        List<Invoice> userInvoices = invoiceDAO.findAllByUser(user);
        assertNotNull(userInvoices);
    }

}
