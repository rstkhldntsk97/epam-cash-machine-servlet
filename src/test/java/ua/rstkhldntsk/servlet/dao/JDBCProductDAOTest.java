package ua.rstkhldntsk.servlet.dao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.database.DBInitializer;
import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.models.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class JDBCProductDAOTest {

    private ProductDAO productDAO;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        productDAO = daoFactory.createProductDao();
    }

    @Test
    public void findAll() {
        List<Product> products = productDAO.findAll();
        assertNotNull(products);
    }

    @Test
    public void findAllByPage() {
        List<Product> products = productDAO.findAllByPage(1, 1);
        assertNotNull(products);
    }

    @Test(expected = NoSuchElementException.class)
    public void findByNameFailed() {
        productDAO.findByName("test111", 1).get();
    }

    @Test
    public void findById(){
        Product product = productDAO.findById(1, 1).get();
        Product product1 = new Product( 1,"bed", BigDecimal.valueOf(100L), 100);
        assertEquals(product1.getCode(), product.getCode());
    }

    @Test(expected = NoSuchElementException.class)
    public void findByIdFailed(){
        productDAO.findById(100, 1).get();
    }

    @Test
    public void update(){
        Product product = new Product(1, "bed", BigDecimal.valueOf(100), 300);
        assertTrue(productDAO.update(product));
    }

    @Test
    public void create(){
        assertTrue(productDAO.create(getProduct()));
    }

    @Test
    public void createTranslate() throws ProductAlreadyExistException {
        Product product = getProduct();
        productDAO.create(product);
        assertTrue(productDAO.createTranslate(product, "тест"));
    }


    @Test(expected = UnsupportedOperationException.class)
    public void delete() {
        productDAO.delete(getProduct());
    }

    private Product getProduct(){
        return new Product("test", BigDecimal.valueOf(100), 100);
    }

}
