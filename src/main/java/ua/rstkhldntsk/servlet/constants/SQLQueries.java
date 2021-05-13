package ua.rstkhldntsk.servlet.constants;

public interface SQLQueries {

    //SQL queries for Users
    String INSERT_USER = "INSERT INTO users (username, password, role_id) VALUES (?,?,?)";
    String FIND_ALL_USERS = "SELECT users.*, role_name FROM users LEFT JOIN roles ON users.role_id = roles.id";
    String FIND_BY_ID = "SELECT users.*, role_name FROM users LEFT JOIN roles ON users.role_id = roles.id WHERE id = ?";
    String FIND_BY_USERNAME = "SELECT users.*, role_name FROM users LEFT JOIN roles ON users.role_id = roles.id WHERE username = ?";

    //SQL queries for Products
    String INSERT_PRODUCT = "INSERT INTO products (product_name, code, price) VALUES (?,?,?)";
    String FIND_ALL_PRODUCTS = "SELECT * FROM products";
    String FIND_PRODUCT_BY_NAME = "SELECT * FROM products WHERE product_name = ?";
    String FIND_PRODUCT_BY_CODE = "SELECT * FROM products WHERE code = ?";

    //SQL queries for Roles


    //SQL queries for Receipts


    //SQL queries for Stock


}
