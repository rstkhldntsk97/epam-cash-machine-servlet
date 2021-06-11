package ua.rstkhldntsk.servlet.utils;

import org.junit.*;
import ua.rstkhldntsk.servlet.exceptions.InvalidInput;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    @Test
    public void productCodeValidateTrue() throws InvalidInput {
        assertEquals(Validator.productCodeValidate("12"), 12);
    }

    @Test(expected = InvalidInput.class)
    public void productCodeValidateThrowException() throws InvalidInput {
        Validator.productCodeValidate("abc");
    }

    @Test
    public void productNameValidateTrue() throws InvalidInput {
        assertEquals(Validator.productNameOnEngValidate("CAR123"), "car123");
    }

    @Test(expected = InvalidInput.class)
    public void productNameValidateThrowException() throws InvalidInput {
        Validator.productNameOnEngValidate("CAR!@#");
    }

    @Test
    public void productPriceValidateTrue() throws InvalidInput {
        BigDecimal expected = BigDecimal.valueOf(Float.parseFloat("10.0"));
        assertEquals(Validator.productPriceValidate("10.0"), expected);
    }

    @Test(expected = InvalidInput.class)
    public void productPriceValidateThrowException() throws InvalidInput {
        Validator.productPriceValidate("-10.0");
    }

    @Test
    public void productQuantityValidateTrue() throws InvalidInput {
        assertEquals(Validator.productQuantityValidate("10"), 10);
    }

    @Test(expected = InvalidInput.class)
    public void productQuantityValidateThrowException() throws InvalidInput {
        Validator.productQuantityValidate("");
    }

    @Test
    public void usernameValidateTrue() throws InvalidInput {
        assertEquals(Validator.usernameValidate("jeffrey"), "jeffrey");
    }

    @Test(expected = InvalidInput.class)
    public void usernameValidateThrowException() throws InvalidInput {
        Validator.usernameValidate("DROP DATABASE servlet_db;");
    }

    @Test
    public void passwordValidateTrue() throws InvalidInput {
        assertEquals(Validator.passwordValidate("password"), "password");
    }

    @Test(expected = InvalidInput.class)
    public void passwordValidateThrowException() throws InvalidInput {
        Validator.passwordValidate("admin");
    }

    @Test
    public void invoiceIdValidateTrue() throws InvalidInput {
        assertEquals(Validator.invoiceIdValidate("12"), 12);
    }

    @Test(expected = InvalidInput.class)
    public void invoiceIdValidateThrowException() throws InvalidInput {
        Validator.invoiceIdValidate("-1");
    }

    @Test
    public void langIdValidateTrue(){
        assertEquals(Validator.languageValidate(null), 1);
        assertEquals(Validator.languageValidate("ua"), 2);
        assertEquals(Validator.languageValidate("en"), 1);
    }

}
