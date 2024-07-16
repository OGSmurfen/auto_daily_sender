package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryptUtil {
    private static final SecureRandom random = new SecureRandom();
    public static byte[] generateSalt(){
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    public static String hashPassword(String password, byte[] salt)throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
    public static boolean validatePassword(String inputPassword, String storedHashPassword, byte[] salt) throws NoSuchAlgorithmException{
        String hashedInputPassword = hashPassword(inputPassword, salt);
        return hashedInputPassword.equals(storedHashPassword);
    }

    public static String hashPassword(String password)throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = messageDigest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
    public static boolean validatePassword(String inputPassword, String storedHashPassword) throws NoSuchAlgorithmException{
        String hashedInputPassword = hashPassword(inputPassword);
        return hashedInputPassword.equals(storedHashPassword);
    }
}
