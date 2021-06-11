package ua.rstkhldntsk.servlet.servlets;

import com.mysql.cj.xdevapi.Schema;
import org.apache.log4j.Logger;
import ua.rstkhldntsk.servlet.exceptions.IdNotExist;
import ua.rstkhldntsk.servlet.exceptions.InvalidInput;
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
import java.util.List;
import java.util.ResourceBundle;

@WebServlet("/editInvoice")
public class SeniorUpdateInvoice extends HttpServlet {

    InvoiceService invoiceService = new InvoiceService();
    private static final Logger LOGGER = Logger.getLogger(SeniorUpdateInvoice.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Invoice> invoices = invoiceService.findAllInvoices();
        req.setAttribute("invoicesFromServer", invoices);
        req.getRequestDispatcher("/editInvoice.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        String lang = (String) session.getAttribute("lang");
        String invoiceId = req.getParameter("id");
        try {
            Invoice invoiceToEdit = invoiceService.findById(invoiceId, lang);
            session.setAttribute("invoice", invoiceToEdit);
            req.setAttribute("invoice", invoiceToEdit);
            resp.sendRedirect(req.getContextPath() + "/currentInvoice.jsp");
            LOGGER.info("invoice #" + invoiceToEdit.getId() + " was picked to edit");
        } catch (InvalidInput invalidInput) {
            session.setAttribute("message", resourceBundle.getString("invalid.input"));
            doGet(req, resp);
        } catch (IdNotExist idNotExist) {
            session.setAttribute("message", resourceBundle.getString("id.not.exist"));
            doGet(req, resp);
        }
    }
}