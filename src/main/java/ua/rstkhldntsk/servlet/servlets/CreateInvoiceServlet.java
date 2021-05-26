package ua.rstkhldntsk.servlet.servlets;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.Product;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.InvoiceService;
import ua.rstkhldntsk.servlet.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createInvoice")
public class CreateInvoiceServlet extends HttpServlet {

    ProductService productService = ProductService.getInstance();
    InvoiceService invoiceService = InvoiceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoiceService.createNewInvoice(invoice);
        try {
            String code = req.getParameter("code");
            Product product = productService.findProductByCode(Long.parseLong(code));
            req.setAttribute("productByCodeFromServer", product);
            req.getRequestDispatcher("/createInvoice.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.getRequestDispatcher("/createInvoice.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        Product product = null;
        try {
            product = productService.findProductByCode(Long.parseLong(code));
        } catch (NumberFormatException e) {

        }
        req.setAttribute("productByCodeFromServer", product);
        req.getRequestDispatcher("/createInvoice.jsp").forward(req, resp);
    }
}
