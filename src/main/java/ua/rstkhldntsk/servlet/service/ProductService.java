package ua.rstkhldntsk.servlet.service;

import ua.rstkhldntsk.servlet.dao.DaoFactory;
import ua.rstkhldntsk.servlet.dao.ProductDAO;
import ua.rstkhldntsk.servlet.dao.impl.JDBCDaoFactory;
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
//        if (instance == null) {
//            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new ProductService();
//                }
//            }
        }
        return instance;
    }

    ProductDAO productDAO = JDBCDaoFactory.getInstance().createProductDao();

    public List<Product> findAll(){
        return productDAO.findAll();
    }

}
