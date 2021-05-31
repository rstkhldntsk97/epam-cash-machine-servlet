package ua.rstkhldntsk.servlet.servlets;


import ua.rstkhldntsk.servlet.exceptions.ItemExistException;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet("/commodityExpert")
public class CommodityExpert extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        req.setAttribute("productsFromServer", products);
        req.getRequestDispatcher("/stock.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(req.getParameter("price")));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        try {
            productService.createProduct(new Product(name, price, quantity));
            session.setAttribute("message", resourceBundle.getString("create.product.success"));
        } catch (ItemExistException e) {
            session.setAttribute("message", resourceBundle.getString("product.exist"));
        }
        resp.sendRedirect(req.getContextPath() + "/home.jsp");
    }
}
