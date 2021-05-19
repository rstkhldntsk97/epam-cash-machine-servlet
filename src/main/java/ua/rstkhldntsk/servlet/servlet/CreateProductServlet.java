package ua.rstkhldntsk.servlet.servlet;

import ua.rstkhldntsk.servlet.model.Product;
import ua.rstkhldntsk.servlet.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

@WebServlet("/createProduct")
public class CreateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("createProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");

        String name = req.getParameter("name");
        Integer code = Integer.parseInt(req.getParameter("password"));
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(req.getParameter("price")));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));

        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setCode(code);
        product.setPrice(price);
        ProductService.getInstance().createProduct(product);
        resp.sendRedirect("/expert");
//        doGet(req, resp);
    }
}
