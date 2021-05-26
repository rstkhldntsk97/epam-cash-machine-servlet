package ua.rstkhldntsk.servlet.dao.interfaces;

import ua.rstkhldntsk.servlet.models.Invoice;
import ua.rstkhldntsk.servlet.models.InvoiceProduct;

import java.util.Set;

public interface InvoiceProductDAO extends GenericDAO<InvoiceProduct> {


    Set<InvoiceProduct> findAllByInvoice(Invoice invoice);
}
