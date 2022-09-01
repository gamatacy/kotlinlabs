package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCreator {
    public static String hash384(String password) {
        MessageDigest mg;
        byte[] digest = null;

        try {
            mg = MessageDigest.getInstance("SHA-384");
            digest = mg.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexDigest = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                hexDigest.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            return (hexDigest.toString());

        } catch (
                NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
