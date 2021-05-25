package ua.rstkhldntsk.servlet.constants;

public interface SQLQueries {

    //SQL queries for Users
    String INSERT_USER = "INSERT INTO users (username, password, role) VALUES (?,?,?)";
    String FIND_ALL_USERS = "SELECT * FROM users";
    String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    String FIND_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    String FIND_USER_ROLE_BY_ID = "SELECT role FROM users WHERE users.id = ?";
    String SQL_CHECK_USER = "SELECT * FROM users WHERE username = ? AND password = ?";


    //SQL queries for Products
    String INSERT_PRODUCT = "INSERT INTO products (name, price, quantity) VALUES (?,?,?)";
    String FIND_ALL_PRODUCTS = "SELECT * FROM products";
    String FIND_PRODUCT_BY_NAME = "SELECT * FROM products WHERE product_name = ?";
    String FIND_PRODUCT_BY_CODE = "SELECT * FROM products WHERE code = ?";

    //SQL queries for Invoice
    String INSERT_NEW_INVOICE = "INSERT INTO invoices (total_price, user_id) VALUES (?,?)";
    String COUNT_INVOICES_FY_USER_ID = "SELECT COUNT(*) FROM invoices WHERE user_id = ?";
    String SELECT_PRODUCTS_BY_INVOICE = "SELECT * FROM invoice_products JOIN products ON products.id = invoice_products.product_id WHERE invoice_id = ?";

}
