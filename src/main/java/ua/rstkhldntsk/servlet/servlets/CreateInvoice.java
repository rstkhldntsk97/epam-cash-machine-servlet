package ua.rstkhldntsk.servlet.servlets;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.exceptions.ItemExistException;
import ua.rstkhldntsk.servlet.exceptions.NotEnoughProduct;
import ua.rstkhldntsk.servlet.exceptions.ProductNotExist;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.services.InvoiceService;
import ua.rstkhldntsk.servlet.services.ProductService;

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
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        String code = req.getParameter("code");
        String quantity = req.getParameter("quantity");
        Product product = null;
        Invoice invoice = (Invoice) session.getAttribute("invoice");
        try {
            product = productService.findProductByCode(Long.parseLong(code));
            BigDecimal price = invoiceService.countPriceForProductByQuantity(Integer.parseInt(quantity), Long.parseLong(code));
            invoiceService.addProductToInvoice(code, quantity, invoice, price);
            session.setAttribute("message", resourceBundle.getString("product.successfully.added"));
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
        } catch (ItemExistException e) {
            session.setAttribute("message", resourceBundle.getString("product.already.in.invoice"));
            LOGGER.debug("Product is already in invoice");
        }
        session.getAttribute("user");
        req.setAttribute("productByCodeFromServer", product);
        req.getRequestDispatcher("/createInvoice.jsp").forward(req, resp);
    }
}
