package ua.rstkhldntsk.servlet.servlets;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.exceptions.InvalidInput;
import ua.rstkhldntsk.servlet.exceptions.ProductAlreadyExistException;
import ua.rstkhldntsk.servlet.exceptions.NotEnoughProduct;
import ua.rstkhldntsk.servlet.exceptions.ProductNotExist;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.services.InvoiceService;
import ua.rstkhldntsk.servlet.services.ProductService;
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

@WebServlet("/createInvoice")
public class CreateInvoice extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateInvoice.class);

    ProductService productService = ProductService.getInstance();
    InvoiceService invoiceService = InvoiceService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: fix this mess
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        Integer langId = Validator.languageValidate((String) session.getAttribute("lang"));
        String parameter1 = req.getParameter("code");
        String parameter2 = req.getParameter("quantity");

        Product product = null;
        Invoice invoice = (Invoice) session.getAttribute("invoice");

        try {
            Integer code = Validator.productCodeValidate(parameter1);
            Integer quantity = Validator.productQuantityValidate(parameter2);
            //try to find product by code
            try {
                product = productService.findProductByCode(code, langId);
                BigDecimal price = invoiceService.countPriceForProductByQuantity(quantity, code, langId);
                invoiceService.addProductToInvoice(code, quantity, invoice, price, langId);
                invoiceService.updateInvoice(invoice);
                session.setAttribute("message",product.getName() + " " +  resourceBundle.getString("product.successfully.added"));
                LOGGER.debug(product + " was successfully added to invoice in quantity of " + quantity);
            } catch (NotEnoughProduct notEnoughProduct) {
                session.setAttribute("message", resourceBundle.getString("product.not.enough"));
                LOGGER.debug("No product with this code", notEnoughProduct);
            } catch (ProductNotExist productNotExist) {
                session.setAttribute("message", resourceBundle.getString("product.not.exist"));
                LOGGER.debug("Product not exist");
            } catch (NumberFormatException notEnoughProduct) {
                session.setAttribute("message", resourceBundle.getString("product.not.enough"));
                LOGGER.debug("Product quantity cast exception");
            } catch (ProductAlreadyExistException e) {
                session.setAttribute("message", resourceBundle.getString("product.already.in.invoice"));
                LOGGER.debug("Product is already in invoice");
            }
            session.getAttribute("user");
            req.setAttribute("productByCodeFromServer", product);
            resp.sendRedirect(req.getContextPath() + "/createInvoice.jsp");
        } catch (NumberFormatException | InvalidInput e ) {
            //try to find product by name
            try {
                String name = Validator.productNameOnEngValidate(parameter1);
                Integer quantity = Validator.productQuantityValidate(parameter2);
                product = productService.findProductByName(name, langId);
                BigDecimal price = invoiceService.countPriceForProductByQuantity(quantity, product.getCode(), langId);
                invoiceService.addProductToInvoice(product.getCode(), quantity, invoice, price, langId);
                session.setAttribute("message", product.getName() + " " + resourceBundle.getString("product.successfully.added"));
                LOGGER.debug(product + " was successfully added to invoice in quantity of " + quantity);
            } catch (NotEnoughProduct notEnoughProduct) {
                session.setAttribute("message", resourceBundle.getString("product.not.enough"));
                LOGGER.debug("No product with this code", notEnoughProduct);
            } catch (NumberFormatException notEnoughProduct) {
                session.setAttribute("message", resourceBundle.getString("product.not.enough"));
                LOGGER.debug("Product quantity cast exception");
            } catch (ProductAlreadyExistException e1) {
                session.setAttribute("message", resourceBundle.getString("product.already.in.invoice"));
                LOGGER.debug("Product is already in invoice");
            } catch (ProductNotExist | InvalidInput productNotExist) {
                session.setAttribute("message", resourceBundle.getString("invalid.input"));
                req.getRequestDispatcher("/createInvoice.jsp").forward(req, resp);
            }
            session.getAttribute("user");
            req.setAttribute("productByCodeFromServer", product);
            resp.sendRedirect(req.getContextPath() + "/createInvoice.jsp");
        }
    }
}
