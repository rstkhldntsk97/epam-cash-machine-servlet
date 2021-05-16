package ua.rstkhldntsk.servlet.servlet;

import ua.rstkhldntsk.servlet.dao.ProductDAO;
import ua.rstkhldntsk.servlet.dao.impl.JDBCDaoFactory;
import ua.rstkhldntsk.servlet.model.Product;
import ua.rstkhldntsk.servlet.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    ProductDAO productDAO = JDBCDaoFactory.getInstance().createProductDao();
    ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        req.setAttribute("products", products);
        req.getServletContext().getRequestDispatcher("/products.jsp").forward(req, resp);

    }
}
