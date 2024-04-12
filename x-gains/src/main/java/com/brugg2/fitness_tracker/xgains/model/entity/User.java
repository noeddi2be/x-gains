package com.brugg2.fitness_tracker.xgains.model.entity;

import java.util.Date;

import com.brugg2.fitness_tracker.xgains.model.service.PasswordHashingService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "account_type", nullable = false, length = 10)
    private String accountType;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "salt")
    private Integer salt;

    @Column(name = "hashed_password", nullable = false, length = 255)
    private String hashedPassword;

    @Column(name = "firstname", nullable = false, length = 255)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 255)
    private String lastname;

    @Column(name = "birthdate")
    private Date birthdate;


    // Setters
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Sets the account type of a user. For simplicity the user account type is
     * specified
     * with a number. Only the right combination of numbers will result in admin.
     * Chose -> 12345678 to set admin.
     * -> For simplicity, the admin role will not be supported in the app. Admin
     * and user will have the same functionality.
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

    public void setSalt() {
        this.salt = PasswordHashingService.setSalt();
    }

    public void setPassword(String password) {
        setSalt();
        this.hashedPassword =  PasswordHashingService.hashPassword(password, salt);
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
}
