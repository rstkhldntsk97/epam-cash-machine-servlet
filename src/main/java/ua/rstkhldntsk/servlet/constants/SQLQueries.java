package ua.rstkhldntsk.servlet.constants;

public interface SQLQueries {

    //SQL queries for Users
    String INSERT_USER = "INSERT INTO user (username, password, role_id) VALUES (?,?,?)";
    String FIND_ALL_USERS = "SELECT * FROM user JOIN role ON user.role_id = role.id";
    String FIND_BY_ID = "SELECT * FROM user JOIN role ON user.role_id = role.id WHERE user.id = ?";
    String FIND_BY_USERNAME = "SELECT * FROM user JOIN role ON user.role_id = role.id WHERE username = ?";
    String FIND_USER_ROLE_BY_ID = "SELECT role_name FROM user JOIN role ON user.role_id = role.id WHERE user.id = ?";
    String SQL_CHECK_USER = "SELECT * FROM user JOIN role ON user.role_id = role.id WHERE username = ? AND password = ?";


    //SQL queries for Products
    String INSERT_PRODUCT = "INSERT INTO product (name, price, quantity) VALUES (?,?,?)";
    String FIND_ALL_PRODUCTS = "SELECT * FROM product";
    String FIND_PRODUCT_BY_NAME = "SELECT * FROM product WHERE product_name = ?";
    String FIND_PRODUCT_BY_CODE = "SELECT * FROM product WHERE code = ?";

    //SQL queries for Invoice
    String INSERT_NEW_INVOICE = "INSERT INTO invoice (user_id) VALUES (?)";
    String UPDATE_INVOICE = "update invoice set total_price = (select sum(price) from invoice_has_product where invoice_id = ?) where invoice.id = ?;";
    String COUNT_INVOICES_FY_USER_ID = "SELECT COUNT(*) FROM invoice WHERE user_id = ?";
    String SELECT_PRODUCTS_BY_INVOICE = "SELECT * FROM invoice_has_product JOIN product ON product.code = invoice_has_product.product_id WHERE invoice_id = ?";

}
