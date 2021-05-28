package ua.rstkhldntsk.servlet.validator;

import org.apache.log4j.Logger;

public class IntegerValidator {

    private static final Logger LOGGER = Logger.getLogger(IntegerValidator.class);

    public static Integer validate(String param) {
        Integer result;
        try {
            result = Integer.parseInt(param);
            if (result < 0) {
                result = null;
                LOGGER.debug("Integer validator: input is less than 0");
            }
        } catch (NumberFormatException e) {
            result = null;
            LOGGER.debug("Integer validator: input is not integer");
        }
        return result;
    }

}
