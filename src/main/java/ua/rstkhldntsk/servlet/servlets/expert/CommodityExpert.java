package ua.rstkhldntsk.servlet.servlets.expert;


import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/commodityExpert")
public class CommodityExpert extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/Servlet_war/home.jsp");
//        req.getRequestDispatcher("/commodityExpert.jsp").forward(req, resp);
//        List<Product> products = productService.findAll();
//        req.setAttribute("productsFromServer", products);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(req.getParameter("price")));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));

        productService.createProduct(new Product(name, price, quantity));

        doGet(req, resp);
    }
}
