package ua.rstkhldntsk.servlet.constants;

public interface SQLQueries {

    //SQL queries for Users
    String INSERT_USER = "INSERT INTO user (username, password, role) VALUES (?,?,?)";
    String FIND_ALL_USERS = "SELECT * FROM user";
    String FIND_BY_ID = "SELECT * FROM user WHERE user.id = ?";
    String FIND_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
    String FIND_USER_ROLE_BY_ID = "SELECT role FROM user user.id = ?";
    String SQL_CHECK_USER = "SELECT * FROM user WHERE username = ? AND password = ?";


    //SQL queries for Products
    String INSERT_PRODUCT = "INSERT INTO product (name, price, quantity) VALUES (?,?,?)";
    String FIND_ALL_PRODUCTS = "SELECT * FROM product";
    String FIND_PRODUCT_BY_NAME = "SELECT * FROM product WHERE product_name = ?";
    String FIND_PRODUCT_BY_CODE = "SELECT * FROM product WHERE code = ?";

    //SQL queries for Invoice
    String INSERT_NEW_INVOICE = "INSERT INTO invoice (user_id) VALUES (?)";
    String UPDATE_INVOICE_TOTAL = "update invoice set total_price = (select sum(price) from invoice_has_product where invoice_id = ?) where invoice.id = ?";
    String COUNT_INVOICES_BY_USER_ID = "SELECT COUNT(*) FROM invoice WHERE user_id = ?";
    String SELECT_PRODUCTS_BY_INVOICE = "SELECT * FROM invoice_has_product JOIN product ON product.code = invoice_has_product.product_id WHERE invoice_id = ?";
    String FIND_INVOICE_BY_ID = "select product.name, product.code, product.price, quantity_in_invoice, created_at, user.username, user.id, user.password, invoice.status from invoice " +
            "join invoice_has_product on invoice.id = invoice_has_product.invoice_id " +
            "join product on invoice_has_product.product_code = product.code " +
            "join user on user.id = invoice.user_id where invoice.id = ?";
    String ADD_PRODUCT_TO_INVOICE = "INSERT INTO invoice_has_product (invoice_id, product_code) VALUES (?,?)";
    String UPDATE_INVOICE_STATUS = "UPDATE invoice SET status = ? WHERE invoice.id = ?";
}
