package ua.rstkhldntsk.servlet.constants;

public interface SQLQueries {

    //SQL queries for Users
    String INSERT_USER = "INSERT INTO users (username, password) VALUES (?,?)";
    String FIND_ALL_USERS = "select users.*, roles.name from user_roles JOIN users ON user_roles.user_id = users.id JOIN roles ON user_roles.role_id = roles.id WHERE users.id;";
    String FIND_BY_ID = "SELECT users.*, role_name FROM users LEFT JOIN roles ON users.role_id = roles.id WHERE id = ?";
    String FIND_BY_USERNAME = "SELECT users.*, role_name FROM users LEFT JOIN roles ON users.role_id = roles.id WHERE username = ?";
    String FIND_USER_ROLE_BY_ID = "select * from user_roles JOIN users ON user_roles.user_id = users.id JOIN roles ON user_roles.role_id = roles.id WHERE users.id = ?;";


    //SQL queries for Products
    String INSERT_PRODUCT = "INSERT INTO products (product_name, code, price, quantity) VALUES (?,?,?,?)";
    String FIND_ALL_PRODUCTS = "SELECT * FROM products";
    String FIND_PRODUCT_BY_NAME = "SELECT * FROM products WHERE product_name = ?";
    String FIND_PRODUCT_BY_CODE = "SELECT * FROM products WHERE code = ?";

}
