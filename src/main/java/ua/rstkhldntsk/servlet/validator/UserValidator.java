package ua.rstkhldntsk.servlet.validator;

import ua.rstkhldntsk.servlet.models.User;

import java.util.HashMap;
import java.util.Map;

import static ua.rstkhldntsk.servlet.constants.ValidatorMessages.*;

public class UserValidator {

    private Map<String, String> errors = new HashMap<>();
    private String usernamePattern = "[A-Za-z]+";
    private String passwordPattern = "[0-9]{5,}";


    public void validate(User user) {
        if (!user.getUsername().matches(usernamePattern)) {
            errors.put(USERNAME_ERROR, USERNAME_MESSAGE);
        }
        if (!user.getPassword().matches(passwordPattern)) {
            errors.put(PASSWORD_ERROR, PASSWORD_MESSAGE);
        }
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
