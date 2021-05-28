package ua.rstkhldntsk.servlet.servlets.cashier;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.User;
import ua.rstkhldntsk.servlet.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/cashier")
public class Cashier extends HttpServlet {


    ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/cashier.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Invoice invoice = new Invoice();
        HttpSession session = req.getSession();
//        invoice.setUser((User) session.getAttribute("user"));

        session.setAttribute("invoice", invoice);

        req.getRequestDispatcher("/createInvoice").forward(req, resp);
    }
}
