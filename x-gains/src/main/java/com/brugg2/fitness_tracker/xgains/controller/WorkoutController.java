package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.entity.Location;
import com.brugg2.fitness_tracker.xgains.model.entity.User;
import com.brugg2.fitness_tracker.xgains.model.entity.Workout;
import com.brugg2.fitness_tracker.xgains.model.service.LocationService;
import com.brugg2.fitness_tracker.xgains.model.service.UserService;
import com.brugg2.fitness_tracker.xgains.model.service.WorkoutService;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    /**
     * Method to create a new user using http request and save to database.
     * Input names of the attributes need to be the java class variable names.
     * 
     * @param workout is input as a JSON object and converted to a Java object by
     *                Spring.
     * @return Returns the saved object in the database in JSON format.
     */
    @PostMapping("/new")
    public ResponseEntity addWorkout(@RequestBody String json) {

        Workout workout = new Workout();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss");

        try {
            JSONObject jsonObject = new JSONObject(json);
            String workoutName = jsonObject.has("workoutName") ? jsonObject.getString("workoutName") : null;
            Date workoutDate = jsonObject.has("workoutDate") ? dateFormat.parse(jsonObject.getString("workoutDate")) : null;
            Integer duration = jsonObject.has("duration") ? jsonObject.getInt("duration") : null;
            int userId = jsonObject.getInt("userId");
            int locationId = jsonObject.getInt("locationId");

            User user = userService.getUserById(userId);
            Location location = locationService.getLocationById(locationId);

            workout.setWorkoutName(workoutName);
            workout.setWorkoutDate(workoutDate);
            workout.setDuration(duration);
            workout.setUser(user);
            workout.setLocation(location);

            workoutService.createWorkout(workout);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());

        }
        return ResponseEntity.ok(workout);
    }

    /**
     * Returns all workouts as a Json object for the specified user.
     * Input Json for the api call needs to contain int value with the user.
     * 
     * @param json method looks for 'userId' which will be extracted from the Json
     *             object.
     * @return Returns all workouts or HttpStatus Not_Found.
     */
    @GetMapping("/all")
    public ResponseEntity getAllWorkouts(@RequestBody String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            int userID = jsonObject.getInt("userId");
            User user = userService.getUserById(userID);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            return ResponseEntity.ok(workoutService.getAllWorkouts(user));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());

        }
    }

    /**
     * Deletes a specific workout.
     * Input is required in json format.
     * 
     * @param workoutId method looks for 'workoutId' in the json payload.
    */
    @DeleteMapping("/delete")
    public ResponseEntity deleteWorkout(@RequestBody String json) {
        try {
            int workoutId = new JSONObject(json).getInt("workoutId");
            if (workoutService.getWorkoutByWorkoutId(workoutId) == null) {
                return ResponseEntity.ok("No workout with ID " + workoutId + ".");
            };

            workoutService.deleteWorkout(workoutId);
            return ResponseEntity.ok("Deletion successful!");

        } catch (Exception e) {
            return ResponseEntity.status(
                HttpStatus.CONFLICT
            ).body(e.toString());
        }
    }

}
