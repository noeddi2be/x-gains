package com.brugg2.fitness_tracker.xgains.model.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordHashingService {

    // Private constructor to prevent instantiation
    private PasswordHashingService() {
        throw new UnsupportedOperationException("PashwordHashingService is a utility class and should not be instantiated");
    }

    private static final Random RANDOM = new Random();

    /**
     * Setting the salt for the user. The method is called when using the setter
     * to set the instance variable hashedPassword.
     * The salt is only reset when the password is changed.
     */
    public static int setSalt() {
        return RANDOM.nextInt() * 100 + RANDOM.nextInt();
    }

    /**
     * Function to hash the passwort. When calling the method a new salt is set for
     * user instance. The salt and the hashed password can be stored in the
     * database.
     * Function can be used for setting the password as well as loging in.
     * 
     * @param password is the password in plain text entered by the user.
     */
    public static String hashPassword(String password, int salt) {
        setSalt();
        String saltedPassword = password + salt;
        String hashedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            hashedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashedPassword;
    }
}
