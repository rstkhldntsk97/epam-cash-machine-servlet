package ua.rstkhldntsk.servlet.services;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.interfaces.ProductDAO;
import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.exceptions.ProductNotExist;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.utils.Page;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private static volatile ProductService instance;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final ProductDAO productDAO = daoFactory.createProductDao();
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

    /**
     * finds page with products
     *
     * @param pageInfo pageInfo
     * @return page with products
     */
    public Page<Product> findAllByPage(Integer pageInfo, String lang) {
        Page<Product> page = new Page<>();
        page.setContent(productDAO.findAllByPage(pageInfo, lang));
        int totalRecords = productDAO.findAll().size();
        page.setTotalRecords(totalRecords);
        int totalPages = (int) Math.ceil(page.getTotalRecords() * 1.0 / page.getMaxResult());
        page.setTotalPages(totalPages);
        return page;
    }


    public void createProduct(Product product, String translateUA, String translateEN) throws ProductAlreadyExistException {
        productDAO.create(product);
        productDAO.createTranslateEN(product, translateEN);
        productDAO.createTranslateUA(product, translateUA);
    }

    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    /**
     * finds product by name
     *
     * @param name name of product
     * @return product
     */
    public Product findProductByName(String name) throws ProductNotExist {
        Optional<Product> product = productDAO.findByName(name);
        if (product.isPresent()) {
            return product.get();
        }
        LOGGER.error("invalid code");
        throw new ProductNotExist();
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
            LOGGER.error("invalid code");
            throw new ProductNotExist();
        }
    }
}
