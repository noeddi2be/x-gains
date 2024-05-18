package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;
import com.brugg2.fitness_tracker.xgains.model.service.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

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
    @PostMapping("/new")
    public ResponseEntity addUser(@RequestBody User user) {
        try {

            userService.createUser(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());

        }
        return ResponseEntity.ok(user.getEmail());
    }

    /**
     * Method to remove a new user using http request and save to database.
     * 
     * @param json input as a JSON object and converted to a String.
     *             The json to be passed needs to have the key: email, value
     *             "email".
     * @return Returns the deleted object in the database in JSON format.
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody String json) {

        User user;

        try {
            JSONObject jsonObject = new JSONObject(json);
            String email = jsonObject.getString("email");

            user = userService.getUserByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            userService.deleteUser(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
        }

        return ResponseEntity.ok(user.getEmail());
    }

    /**
     * Method to view user details.
     * 
     * @param userID int
     * @return User object in Json format.
     */
    @PostMapping("/account")
    public ResponseEntity getAccountDetails(@RequestBody String json) {

        JSONObject jsonObject;
        int userID;
        User user;


        try {
            jsonObject = new JSONObject(json);
            userID = jsonObject.getInt("userID");
            user = userService.getUserById(userID);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            jsonObject.clear();
            jsonObject.put("userID", user.getUserId());
            jsonObject.put("accountType", user.getAccountType());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("firstname", user.getFirstname());
            jsonObject.put("lastname", user.getLastname());
            jsonObject.put("birthdate", user.getBirthdate());
            String userdata = jsonObject.toString(4);

            return ResponseEntity.ok(userdata);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString()); 
        }
    }

}
