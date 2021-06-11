package ua.rstkhldntsk.servlet.constants;

public interface SQLQueries {

    //SQL queries for user table
    String INSERT_USER = "INSERT INTO user (username, password, role) VALUES (?,?,?)";
    String FIND_ALL_USERS = "SELECT * FROM user";
    String FIND_USER_BY_ID = "SELECT * FROM user WHERE user.id = ?";
    String FIND_BY_USERNAME = "SELECT * FROM user WHERE username = ? AND password = ?";
    String CHECK_USER = "SELECT * FROM user WHERE username = ? AND password = ?";


    //SQL queries for product table
    String INSERT_PRODUCT = "INSERT INTO product (price, quantity) VALUES (?,?)";
    String INSERT_PRODUCT_EN = "INSERT INTO product_translate(product_code, lang_id, name) values (?, 1, ?), (? ,2, ?)";

    String FIND_ALL_PRODUCTS = "SELECT product.code, product.quantity,product.price,product_translate.name FROM product JOIN product_translate ON product.code = product_translate.product_code join language on language.id = product_translate.lang_id where language.lang = 'EN'";

    String FIND_ALL_PRODUCTS_BY_LANG = "SELECT product.code, product.quantity,product.price,product_translate.name FROM product JOIN product_translate ON product.code = product_translate.product_code join language on language.id = product_translate.lang_id where language.id = ?";

    String FIND_PRODUCT_BY_NAME = "SELECT * FROM product JOIN product_translate on product.code=product_translate.product_code WHERE lang_id = ? and name = ?";
    String FIND_PRODUCT_BY_CODE = "SELECT * FROM product JOIN product_translate on product.code=product_translate.product_code WHERE lang_id = ? and code = ?";

    //SQL queries for invoice and invoice_has_product tables
    String CREATE_INVOICE = "INSERT INTO invoice (user_id) VALUES (?)";
    String UPDATE_PRODUCT = "update product set quantity = ? WHERE product.code = ?";
    String UPDATE_INVOICE = "update invoice set total_price = (select sum(price) from invoice_has_product where invoice_id = ?), status = ? WHERE invoice.id = ?";
    String FIND_PRODUCTS_BY_INVOICE = "SELECT * FROM invoice_has_product JOIN product ON product.code = invoice_has_product.product_id WHERE invoice_id = ?";
    String FIND_INVOICE_BY_ID = "select * from invoice " +
            "join invoice_has_product on invoice.id = invoice_has_product.invoice_id " +
            "join product on invoice_has_product.product_code = product.code JOIN product_translate on product.code=product_translate.product_code " +
            "join user on user.id = invoice.user_id where lang_id = ? and invoice.id = ?";
    String ADD_PRODUCT_TO_INVOICE = "INSERT INTO invoice_has_product (invoice_id, product_code, quantity_in_invoice, price) VALUES (?,?,?, ?)";
    String FIND_ALL_INVOICES = "select * from invoice join user on user.id = invoice.user_id where status = 'CLOSED'";
    String FIND_ALL_INVOICES_BY_USER = "select * from invoice join user on user.id = invoice.user_id where user.id = ?";
    String FIND_INVOICE_BY_USER_ID_AND_INVOICE_ID = "select * from invoice join user on user.id = invoice.user_id join invoice_has_product on invoice.id = invoice_has_product.invoice_id " +
            "join product on product.code = invoice_has_product.product_code where user.id = ? and invoice.id = ?";
    String DELETE_INVOICE = "DELETE FROM invoice WHERE id = ?";
    String DELETE_PRODUCT_FROM_INVOICE = "delete from invoice_has_product Where invoice_id = ? and product_code = ?";
}
