package ua.rstkhldntsk.servlet.servlets.cashier;

import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.exceptions.NotEnoughProduct;
import ua.rstkhldntsk.servlet.exceptions.ProductNotExist;
import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.InvoiceService;
import ua.rstkhldntsk.servlet.services.ProductService;
import ua.rstkhldntsk.servlet.servlets.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createInvoice")
public class CreateInvoice extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateInvoice.class);

    ProductService productService = ProductService.getInstance();
    InvoiceService invoiceService = InvoiceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Invoice invoice = new Invoice();
        session.setAttribute("invoice", invoice);
        invoice.setUser(user);
        invoiceService.createNewInvoice(invoice);
//        try {
//            String code = req.getParameter("code");
//            Product product = productService.findProductByCode(Long.parseLong(code));
//            invoiceService.addProductToInvoice(code, invoice);
//            req.setAttribute("productByCodeFromServer", product);
//            req.getRequestDispatcher("/createInvoice.jsp").forward(req, resp);
//
//        } catch (NumberFormatException e) {
            req.getRequestDispatcher("/createInvoice.jsp").forward(req, resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String code = req.getParameter("code");
        String quantity = req.getParameter("quantity");
        Product product = null;
        Invoice invoice = (Invoice) session.getAttribute("invoice");
        try {
            product = productService.findProductByCode(Long.parseLong(code));
            invoiceService.addProductToInvoice(code,quantity, invoice);
            LOGGER.debug(product + " was successfully added to invoice in quantity of " + quantity);
        } catch (NumberFormatException e) {
            LOGGER.debug("No product with this code", e);
            // show error message to user
        } catch (ProductNotExist productNotExist) {
            LOGGER.debug("Product not exist");
            // show error message to user
        } catch (NotEnoughProduct notEnoughProduct) {
            // show error message to user
        }
        session.getAttribute("user");
//        Invoice invoice = (Invoice)session.getAttribute("invoice");
//        invoiceService.addProductToInvoice(code,invoice);
        req.setAttribute("productByCodeFromServer", product);
        req.getRequestDispatcher("/createInvoice.jsp").forward(req, resp);
    }
}
