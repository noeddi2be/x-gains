package com.brugg2.fitness_tracker.xgains.model.entity;

import java.util.Date;

import java.util.List;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
@Component
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "account_type", nullable = false, length = 10)
    private String accountType;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = true, length = 255)
    private String password;

    @Column(name = "firstname", nullable = false, length = 255)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 255)
    private String lastname;

    @Column(name = "birthdate")
    private Date birthdate;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Workout> workouts;



    // Setters
    public void setUserID(int userID) {
        this.userId = userID;
    }

    /**
     * Sets the account type of a user. For simplicity the user account type is
     * specified with a number. Only the right combination of numbers will result in admin.
     * Chose -> 123456789 to set admin.
     * -> For simplicity, the admin role will not be supported in the app. Admin
     * and user will have the same functionality during first iteration.
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
        this.password = password;
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
    public int getUserId() {
        return this.userId;
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
        return this.password;
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
