package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.dao.WorkoutRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.Location;
import com.brugg2.fitness_tracker.xgains.model.entity.User;
import com.brugg2.fitness_tracker.xgains.model.entity.Workout;
import com.brugg2.fitness_tracker.xgains.model.service.LocationService;
import com.brugg2.fitness_tracker.xgains.model.service.UserService;
import com.brugg2.fitness_tracker.xgains.model.service.WorkoutService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     *                manually parsing. 
     * @return String "Workout created!"
     */
    @Operation(summary = "Add a new workout", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "New workout details", required = true, content = @Content(
            mediaType = "application/json", examples = @ExampleObject(value = """
                {
                    "workoutName": "Evening Workout",
                    "workoutDate": "2024-05-25",
                    "duration": 60,
                    "location": "Brugg"
                }
            """)
            )
        )
    )
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = """
                {
                    "workoutId": 1,
                    "workoutName": "Evening Workout",
                    "workoutDate": "2024-01-25T17:30:00.000+00:00",
                    "duration": 60,
                    "user": {
                      "userId": 9999,
                      "accountType": "user",
                      "username": "Emy",
                      "email": "emily.sharp@gmail.com",
                      "firstname": "Emily",
                      "lastname": "Sharp",
                      "birthdate": "1979-10-11T23:00:00.000+00:00"
                    },
                    "location": {
                      "locationId": 20,
                      "locationName": "Brugg"
                    }
                  }
            """)
        )
    )
    @PostMapping("/new")
    public ResponseEntity addWorkout(@RequestBody Map<String, Object> json, @AuthenticationPrincipal UserDetails userDetails) {

        Workout workout = new Workout();
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));  // Date Time-Zone not alligned with frontend! TBD

        try {
            JSONObject jsonObject = new JSONObject(json);
            String workoutName = jsonObject.has("workoutName") ? jsonObject.getString("workoutName") : null;
            Date workoutDate = jsonObject.has("workoutDate") ? dateFormat.parse(jsonObject.getString("workoutDate")) : null;
            Integer duration = jsonObject.has("duration") ? jsonObject.getInt("duration") : null;
            String locationName = jsonObject.has("location") ? jsonObject.getString("location") : null;

            User user = userService.getUserByUsername(userDetails.getUsername()).get();
            Location location = locationService.getLocationByName(locationName);

            workout.setWorkoutName(workoutName);
            workout.setWorkoutDate(workoutDate);
            workout.setDuration(duration == null ? 0 : duration); 
            workout.setUser(user);
            workout.setLocation(location);

            workoutService.createWorkout(workout);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error! -> " + e.toString());

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
    @Operation(summary = "Return all workouts of a user")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = """
                [
                    {
                        "workoutId": 1,
                        "workoutName": "Evening Workout",
                        "workoutDate": "2024-01-25T17:30:00.000+00:00",
                        "duration": 60,
                        "user": {
                            "userId": 9999,
                            "accountType": "user",
                            "username": "Emy",
                            "email": "emily.sharp@gmail.com",
                            "firstname": "Emily",
                            "lastname": "Sharp",
                            "birthdate": "1979-10-11T23:00:00.000+00:00"
                        },
                        "location": {
                            "locationId": 20,
                            "locationName": "Brugg"
                        }
                    }
                ]
            """)
        )
    )
    @GetMapping("/all")
    public ResponseEntity getAllWorkouts(@AuthenticationPrincipal UserDetails userDetails) {

        try {
            User user = userService.getUserByUsername(userDetails.getUsername()).get();

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            return ResponseEntity.ok(workoutService.getAllWorkouts(user));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());

        }
    }

    /**
     * Retrieve a specific workout.
     * 
     * @param workoutId method looks for 'workoutName'. 
     */
    @Operation(summary = "Get a workout workout by name", 
        parameters = {
            @Parameter(name = "workoutName", 
                description = "Provide the name of a workout",
                required = true, 
                example = "Morning Workout 04-01" 
            )
        } 
    )
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = """
                [
                    {
                        "workoutId": 1,
                        "workoutName": "Morning Workout 04-01",
                        "workoutDate": "2024-01-25T17:30:00.000+00:00",
                        "duration": 60,
                        "user": {
                            "userId": 9999,
                            "accountType": "user",
                            "username": "Emy",
                            "email": "emily.sharp@gmail.com",
                            "firstname": "Emily",
                            "lastname": "Sharp",
                            "birthdate": "1979-10-11T23:00:00.000+00:00"
                        },
                        "location": {
                            "locationId": 20,
                            "locationName": "Brugg"
                        }
                    }
                ]
                    
            """)
        )
    )
    @GetMapping("/name")
    public ResponseEntity getWorkoutByName(@RequestParam String workoutName, @AuthenticationPrincipal UserDetails userDetails) {

        try {
            Workout workout = workoutService.getWorkoutByName(workoutName);

            if (workout == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found");
            }

            return ResponseEntity.ok(workoutService.getWorkoutByName(workoutName));

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
    @Operation(summary = "Delete a workout", 
        parameters = {
            @Parameter(name = "workoutId", 
                description = "Provide workout ID as request parameter", 
                required = true, 
                example = "9999" 
            )
        } 
    )
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = "Deletion successful!"
            )
        )
    )
    @DeleteMapping("/delete")
    public ResponseEntity deleteWorkout(@RequestParam Integer workoutId, @AuthenticationPrincipal UserDetails userDetails) {

        try {
            if (workoutService.getWorkoutByWorkoutId(workoutId) == null) {
                return ResponseEntity.ok("No workout with ID " + workoutId + ".");
            }

            if (workoutService.getWorkoutByWorkoutId(workoutId).getUser() != userService
                .getUserByUsername(userDetails.getUsername()).get()) {
                    return ResponseEntity.ok("Not allowed to delete this workout " + workoutId);
            }

            workoutService.deleteWorkout(workoutId);
            return ResponseEntity.ok("Deletion successful!");

        } catch (Exception e) {
            return ResponseEntity.status(
                    HttpStatus.CONFLICT).body(e.toString());
        }
    }

}
