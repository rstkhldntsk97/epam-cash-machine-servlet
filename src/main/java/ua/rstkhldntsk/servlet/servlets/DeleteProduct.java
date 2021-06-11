package ua.rstkhldntsk.servlet.servlets;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.exceptions.InvalidInput;
import ua.rstkhldntsk.servlet.exceptions.ProductNotExist;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.services.InvoiceService;
import ua.rstkhldntsk.servlet.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/deleteProduct")
public class DeleteProduct extends HttpServlet {

    private final InvoiceService invoiceService = InvoiceService.getInstance();
    private static final Logger LOGGER = Logger.getLogger(DeleteProduct.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        Invoice invoice = (Invoice) session.getAttribute("invoice");
        String lang = (String) session.getAttribute("lang");
        String productName = req.getParameter("product");
        try {
            invoiceService.deleteProductFromInvoice(productName, invoice.getId(), lang);
            session.setAttribute("message",resourceBundle.getString("product.deleted"));
        } catch (InvalidInput | ProductNotExist invalidInput) {
            session.setAttribute("message",resourceBundle.getString("invalid.input"));
            LOGGER.error("wrong product name");
        }
        resp.sendRedirect(req.getContextPath() + "/editInvoice");

    }
}
