package ua.rstkhldntsk.servlet.dao;

import ua.rstkhldntsk.servlet.model.Invoice;
import ua.rstkhldntsk.servlet.model.InvoiceProduct;

import java.util.Set;

public interface InvoiceProductDAO extends GenericDAO<InvoiceProduct> {


    Set<InvoiceProduct> findAllByInvoice(Invoice invoice);
}
