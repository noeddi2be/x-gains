package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.entity.User;
import com.brugg2.fitness_tracker.xgains.model.entity.Workout;
import com.brugg2.fitness_tracker.xgains.model.service.UserService;
import com.brugg2.fitness_tracker.xgains.model.service.WorkoutService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;

    /**
     * Method to create a new user using http request and save to database.
     * Input names of the attributes need to be the java class variable names.
     * 
     * @param workout is input as a JSON object and converted to a Java object by
     *                Spring.
     * @return Returns the saved object in the database in JSON format.
     */
    @PostMapping("/new")
    public ResponseEntity addWorkout(@RequestBody Workout workout) {
        try {
            workoutService.createWorkout(workout);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");

        }
        return ResponseEntity.ok(workout);
    }

    /**
     * Returns all workouts as a Json object for the specified user.
     * 
     * @param userID int userID will be converted to a Java object by Spring.
     * @return Returns all workouts or HttpStatus Not_Found.
     */
    @GetMapping("/all")
    public ResponseEntity getAllWorkouts(@RequestParam int userID) {
        try {
            User user = userService.getUserById(userID);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            return ResponseEntity.ok(workoutService.getAllWorkouts(user));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());

        }
    }

}
