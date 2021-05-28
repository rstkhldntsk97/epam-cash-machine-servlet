package ua.rstkhldntsk.servlet.utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {

    private static final Logger log = Logger.getLogger(Encoder.class);

    public static String encodePassword(String password) {
        String hashedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
            throw new RuntimeException();
        }
        return hashedPassword;
    }

}
