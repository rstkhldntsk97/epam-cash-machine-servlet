package ua.rstkhldntsk.servlet.services;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.JDBCProductDAO;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.dao.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.exceptions.ItemExistException;
import ua.rstkhldntsk.servlet.exceptions.ProductNotExist;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.utils.Page;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private static volatile ProductService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    ProductDAO productDAO = JDBCDaoFactory.getInstance().createProductDao();
    private static final Logger LOGGER = Logger.getLogger(ProductService.class);

    private ProductService() {
    }

    /**
     * @return instance
     */
    public static ProductService getInstance() {
        if (instance == null) {
            synchronized (ProductService.class) {
                if (instance == null) {
                    instance = new ProductService();
                }
            }
        }
        return instance;
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }


    public void createProduct(Product product) throws ItemExistException {
        productDAO.create(product);
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
    public Product findProductByCode(Long code) throws ProductNotExist {
        Optional<Product> product = productDAO.findByCode(code);
        if (product.isPresent()) {
            return product.get();
        } else {
            LOGGER.debug("invalid code");
            throw new ProductNotExist();
        }
    }
}
