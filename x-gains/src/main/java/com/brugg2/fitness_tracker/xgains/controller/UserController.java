package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;
import com.brugg2.fitness_tracker.xgains.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder) {
        this.encoder= passwordEncoder;
    }

    /**
     * Method to create a new user and save it to the database.
     * Input names of the attributes need to be the java class variable names.
     * 
     * @param user is input as a JSON object and converted to a Java object.
     * @return Returns a response entity with success or error message.
     */
        @Operation(summary = "Create a new user", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "New user details", required = true, content = @Content(
            mediaType = "application/json", examples = @ExampleObject(value = """
                {
                    "username": "007",
                    "email": "james.bond@secretservice.uk",
                    "password": "password",
                    "firstname": "James",
                    "lastname": "Bond",
                    "birthdate": "2024-11-11"
                }
            """)
            )
        )
    )
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = "User 1 created!"
            )
        )
    )
    @PostMapping("/new")
    public ResponseEntity addUser(@RequestBody Map<String, Object> json) {

        User user = new User();
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Date Time-Zone not alligned with frontend! TBD

        try {
            JSONObject jsonObject = new JSONObject(json);
            //Integer accountType = jsonObject.has("accountType") ? jsonObject.getInt("accountType") : null;
            String username = jsonObject.has("username") ? jsonObject.getString("username") : null;
            String email = jsonObject.has("email") ? jsonObject.getString("email") : null;
            String password = jsonObject.has("password") ? jsonObject.getString("password") : null;
            String firstname = jsonObject.has("firstname") ? jsonObject.getString("firstname") : null;
            String lastname = jsonObject.has("lastname") ? jsonObject.getString("lastname") : null;
            Date birthdate = jsonObject.has("birthdate") ? dateFormat.parse(jsonObject.getString("birthdate")) : null;

            if (userService.getUserByEmail(email) != null) {
                return ResponseEntity.internalServerError().body("User with email: " + email + " already exists!");
            }

            if (userService.getUserByUsername(username).isPresent()) {
                return ResponseEntity.internalServerError().body("User with username: " + username + " already exists!");
            }

            user.setAccountType(0);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(encoder.encode(password));
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setBirthdate(birthdate);
            
            userService.createUser(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error! -> " + e.toString());

        }
        return ResponseEntity.ok("User " + user.getUserId() + " created!");
    }

    /**
     * Method to remove a new user from the database.
     * Gets user information from AuthenticationPrincipal.
     * 
     * @param UserDetails User needs to be logged in.
     * @return Returns the email of the deleted user and success message.
     */
    @Operation(summary = "Delete user account")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = "Account " + "james.bond@secretservice.uk" + " deleted!"
            )
        )
    )
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal UserDetails userDetails) {

        User user = new User();

        try {
            user = userService.getUserByUsername(userDetails.getUsername()).get();
            userService.deleteUser(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
        }

        return ResponseEntity.ok("Account " + user.getEmail() + " deleted!");
    }

    /**
     * Method to retrieve a user from the database.
     * 
     * @param UserDetails User needs to be logged in.
     * @return Returns the account details of the user in json format.
     */
    @Operation(summary = "Get user details")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = ""
            )
        )
    )
    @GetMapping("/account")
    public ResponseEntity getAccountDetails(@AuthenticationPrincipal UserDetails userDetails) {

        User user = new User();

        try {
            user = userService.getUserByUsername(userDetails.getUsername()).get();

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString()); 
        }
    }

}
