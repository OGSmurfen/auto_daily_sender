package utils;

import java.security.SecureRandom;
import java.util.Base64;

public class VerificationUtil {
    private static final SecureRandom random = new SecureRandom();

    private static String generateVerificationCode(){
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
