package ua.rstkhldntsk.servlet.servlets;

import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/stock")
public class Stock extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        req.setAttribute("productsFromServer", products);
        req.getRequestDispatcher("/stock.jsp").forward(req, resp);
    }
}
