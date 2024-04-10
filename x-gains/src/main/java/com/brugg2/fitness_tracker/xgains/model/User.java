package com.brugg2.fitness_tracker.xgains.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class User {

    private int userID;
    private String accountType;
    private String username;
    private String email;
    private int salt;
    private String hashedPassword;
    private String firstname;
    private String lastname;
    private Date birthdate;

    private static final Random RANDOM = new Random();


    // Setters
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Sets the account type of a user. For simplicity the user account type is
     * specified
     * with a number. Only the right combination of numbers will result in admin.
     * Chose -> 12345678 to set admin.
     * @param accountType all numbers except the special number result in user.
     */
    public void setAccountType(int accountType) {
        if (accountType == 123456789) {
            this.accountType = "admin";
        } else {
            this.accountType = "user";
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
       String newPassword =  hashPassword(password);
       this.hashedPassword = newPassword; 
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthdate(Date birthdate){
        this.birthdate = birthdate;
    }


    // Getters
    public int getUserID() {
        return this.userID;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }


    /**
     * Setting the salt for the user. The method is called when using the setter
     * to set the instance variable hashedPassword.
     * The salt is only reset when the password is changed.
     */
    private void setSalt() {
        int randomNumber = RANDOM.nextInt() * 100 + RANDOM.nextInt();
        this.salt = randomNumber;
    }


    /**
     * Function to hash the passwort. When calling the method a new salt is set for
     * user instance. The salt and the hashed password can be stored in the database.
     * Function can be used for setting the password as well as loging in. 
     * @param password is the password in plain text entered by the user.
     */
    public String hashPassword(String password) {
        setSalt();
        String saltedPassword = password + this.salt;

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
