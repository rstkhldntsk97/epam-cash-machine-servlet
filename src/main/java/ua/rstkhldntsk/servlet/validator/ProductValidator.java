package ua.rstkhldntsk.servlet.validator;

import ua.rstkhldntsk.servlet.models.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static ua.rstkhldntsk.servlet.constants.ValidatorMessages.*;

public class ProductValidator {

    private Map<String, String> errors = new HashMap<>();


    public void validate(Product product) {
        if (product.getCode() == null) {
            errors.put(CODE_ERROR, CODE_MESSAGE);
        }
        if (product.getName() == null || product.getName().equals("")) {
            errors.put(NAME_ERROR, NAME_MESSAGE);
        }
        if (product.getPrice().equals(BigDecimal.ZERO) || product.getPrice().doubleValue() < 0.1) {
            errors.put(PRICE_ERROR, PRICE_MESSAGE);
        }
        if (product.getQuantity() == null || product.getQuantity() <= 0) {
            errors.put(QUANTITY_ERROR, QUANTITY_MESSAGE);
        }
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
