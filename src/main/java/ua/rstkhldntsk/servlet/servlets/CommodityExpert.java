package ua.rstkhldntsk.servlet.servlets;


import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.exceptions.InvalidInput;
import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.services.ProductService;
import ua.rstkhldntsk.servlet.utils.Page;
import ua.rstkhldntsk.servlet.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

@WebServlet("/commodityExpert")
public class CommodityExpert extends HttpServlet {

    ProductService productService = ProductService.getInstance();
    private static final Logger LOGGER = Logger.getLogger(CommodityExpert.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String lang = (String) session.getAttribute("lang");
        Integer langId = Validator.languageValidate(lang);
        int page = 1;
        if(req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        Page<Product> products = productService.findAllByPage(page, langId);

        req.setAttribute("productsFromServer", products.getContent());
        req.setAttribute("noOfPages", products.getTotalPages());
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher("/stock.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        String nameEN = req.getParameter("nameEN");
        String nameUA = req.getParameter("nameUA");
        String priceStr = req.getParameter("price");
        String  quantityStr = req.getParameter("quantity");
        try {
            productService.createProduct(priceStr, quantityStr, nameUA, nameEN);
            session.setAttribute("message", resourceBundle.getString("create.product.success"));
        } catch (InvalidInput invalidInput) {
            session.setAttribute("message", resourceBundle.getString("invalid.input"));
            LOGGER.error("Validator exception");
        } catch (ProductAlreadyExistException e) {
            session.setAttribute("message", resourceBundle.getString("product.exist"));
            LOGGER.error("product exist");
        }
        resp.sendRedirect(req.getContextPath() + "/home.jsp");
    }
}
