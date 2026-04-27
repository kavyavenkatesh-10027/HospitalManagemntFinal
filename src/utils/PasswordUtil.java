//package utils;
//
//import java.security.MessageDigest;
//import java.security.SecureRandom;
//import java.util.Base64;
//
//public class PasswordUtil {
//
//    public static String generateSalt() {
//        byte[] salt = new byte[16];
//        new SecureRandom().nextBytes(salt);
//        return Base64.getEncoder().encodeToString(salt);
//    }
//
//    public static String hashPassword(String password, String salt) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hash = md.digest((password + salt).getBytes());
//            return Base64.getEncoder().encodeToString(hash);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static String generateStoredPassword(String password){
//        String salt = generateSalt();
//        String hash = hashPassword(password, salt);
//        return salt + ":" + hash;
//    }
//
//    public static boolean verifyPassword(String inputPassword, String stored){
//        String[] parts = stored.split(":");
//
//        if (parts.length != 2) return false;
//
//        String salt = parts[0];
//        String hash = parts[1];
//
//        String inputHash = hashPassword(inputPassword, salt);
//        return inputHash.equals(hash);
//    }
//}