package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;
import com.brugg2.fitness_tracker.xgains.model.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    /**
     * Method to create a new user using http request and save to database.
     * Input names of the attributes need to be the java class variable names.
     * 
     * @param user is input as a JSON object and converted to a Java object by
     *             Spring.
     * @return Returns the saved object in the database in JSON format.
     */
    @Autowired
    @PostMapping("/new")
    public ResponseEntity addUser(@RequestBody User user) {
        try {

            userService.createUser(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());

        }
        return ResponseEntity.ok(user);
    }

    /**
     * Method to remove a new user using http request and save to database.
     * Input names of the attributes need to be the java class variable names.
     * Cannot delete user without fk references (workouts) are deleted as well.
     * 
     * @param json input as a JSON object and converted to a String.
     *             The json to be passed needs to have the key: email, value
     *             "email".
     * @return Returns the deleted object in the database in JSON format.
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteUser(@RequestBody String json) {

        JSONObject jsonObject;
        String email;
        User user;

        try {
            jsonObject = new JSONObject(json);
            email = jsonObject.getString("email");
            user = userService.getUserByEmail(email);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            userService.deleteUser(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
        }

        return ResponseEntity.ok(user);
    }

    /**
     * Method to view user details.
     * Input is the prinamry key userID.
     * Also returns the hashed password :D. No bueno.
     * 
     * @param userID int
     * @return User object in Json format.
     */
    @GetMapping("account")
    public ResponseEntity getAccountDetails(@RequestParam int userID) {
        try {
            User user = userService.getUserById(userID);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
        }
    }

}
