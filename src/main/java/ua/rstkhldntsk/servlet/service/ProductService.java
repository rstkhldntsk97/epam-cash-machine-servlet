package ua.rstkhldntsk.servlet.service;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.ProductDAO;
import ua.rstkhldntsk.servlet.dao.impl.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.exceptions.ProductExistException;
import ua.rstkhldntsk.servlet.model.Product;

import java.util.List;

public class ProductService {

    private static volatile ProductService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private ProductService() {
    }

    /**
     * @return instance
     */
    public static ProductService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new ProductService();
                }
            }
        }
        return instance;
    }

    ProductDAO productDAO = JDBCDaoFactory.getInstance().createProductDao();

    public List<Product> findAll() {
        return productDAO.findAll();
    }


    public boolean createProduct(Product product) {
        try {
            ProductDAO productDao = daoFactory.createProductDao();
            productDao.create(product);
            return true;
        } catch (ProductExistException e) {

        }
        return false;
    }


    /**
     * finds product by name
     *
     * @param name name of product
     * @return product
     */
    public Product findProductByName(String name) {
        ProductDAO productDao = daoFactory.createProductDao();
        return productDao.findByName(name).orElseThrow(RuntimeException::new);
        //TODO change on my own exc
    }

    /**
     * finds product by code
     *
     * @param code code of product
     * @return product
     */
    public Product findProductByCode(Integer code) {
        ProductDAO productDao = daoFactory.createProductDao();
        return productDao.findByCode(code).orElseThrow(RuntimeException::new);
        //TODO change on my own exc
    }
}
