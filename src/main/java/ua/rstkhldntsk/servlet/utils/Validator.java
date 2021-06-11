package ua.rstkhldntsk.servlet.utils;

import ua.rstkhldntsk.servlet.exceptions.InvalidInput;

import java.math.BigDecimal;

public class Validator {

    public static Integer productCodeValidate(String code) throws InvalidInput {
        String pattern = "[0-9]+";
        if (!code.matches(pattern)) {
            throw new InvalidInput();
        }
        return Integer.parseInt(code);
    }

    public static String productNameOnEngValidate(String name) throws InvalidInput {
        String pattern = "[A-Za-z0-9 ']+";
        if (!name.matches(pattern)) {
            throw new InvalidInput();
        }
        return name.toLowerCase();
    }

    public static String productNameOnUaValidate(String name) throws InvalidInput {
        String pattern = "[А-ЯЄЇа-яєї0-9 ']+";
        if (!name.matches(pattern)) {
            throw new InvalidInput();
        }
        return name.toLowerCase();
    }

    public static BigDecimal productPriceValidate(String price) throws InvalidInput {
        String pattern = "[0-9,.]+";
        if (!price.matches(pattern)) {
            throw new InvalidInput();
        }
        return BigDecimal.valueOf(Float.parseFloat(price));
    }

    public static Integer productQuantityValidate(String quantity) throws InvalidInput {
        String pattern = "[0-9.,]+";
        if (!quantity.matches(pattern)) {
            throw new InvalidInput();
        }
        return Integer.parseInt(quantity);
    }

    public static String usernameValidate(String username) throws InvalidInput {
        String pattern = "[A-Za-z1]+";
        if (!username.matches(pattern)) {
            throw new InvalidInput();
        }
        return username;
    }

    public static String passwordValidate(String password) throws InvalidInput{
        String pattern = "[A-Za-z0-9]{8,}";
        if (!password.matches(pattern)) {
            throw new InvalidInput();
        }
        return password;
    }

    public static Integer invoiceIdValidate(String id) throws InvalidInput{
        String pattern = "[0-9]+";
        if (!id.matches(pattern)) {
            throw new InvalidInput();
        }
        return Integer.parseInt(id);
    }

    public static Integer languageValidate(String lang) {
        Integer langId=1;
        if (lang!=null) {
            if (lang.equals("ua")) {
                langId = 2;
                return langId;
            }
        }
        return langId;
    }
}
