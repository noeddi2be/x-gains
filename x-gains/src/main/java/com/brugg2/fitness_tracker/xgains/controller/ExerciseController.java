package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.entity.Exercise;
import com.brugg2.fitness_tracker.xgains.model.entity.Workout;
import com.brugg2.fitness_tracker.xgains.model.service.ExerciseService;
import com.brugg2.fitness_tracker.xgains.model.service.UserService;
import com.brugg2.fitness_tracker.xgains.model.service.WorkoutService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired 
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;

    /**
     * Method to create a new exercisd using. 
     * Input names of the attributes need to be the java class variable names.
     * 
     * @param exercise is a JSON object and converted to a Java object by Spring.
     * @return Returns the saved object in the database in JSON format.
     */
    @Operation(summary = "Add a new exercise", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "New exercise details", required = true, content = @Content(
            mediaType = "application/json", examples = @ExampleObject(value = """
                {
                    "exerciseName": "Running",
                    "exerciseDescription": "Cardio exercise outdoors or on a treadmill",
                    "weight": 15,
                    "repetition": 10,
                    "numberOfSets": 3,
                    "time": 1800,
                    "distance": 6000,
                    "workoutId": 9999
                }
            """)
            )
        )
    )
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = "Exercise 1 created!"
            )
        )
    )
    @PostMapping("/new")
    public ResponseEntity addExercise(@RequestBody Map<String, Object> json, @AuthenticationPrincipal UserDetails userDetails) {

        Exercise exercise = new Exercise();

        try {
            JSONObject jsonObject = new JSONObject(json);
            String exerciseName = jsonObject.has("exerciseName") ? jsonObject.getString("exerciseName") : null;
            String exerciseDescription = jsonObject.has("exerciseDescription") ? jsonObject.getString("exerciseDescription") : null;
            Integer weight = jsonObject.has("weight") ? jsonObject.getInt("weight") : null;
            Integer repetition = jsonObject.has("repetition") ? jsonObject.getInt("repetition") : null;
            Integer numberOfSets = jsonObject.has("numberOfSets") ? jsonObject.getInt("numberOfSets") : null;
            Integer time = jsonObject.has("time") ? jsonObject.getInt("time") : null;
            Integer distance = jsonObject.has("distance") ? jsonObject.getInt("distance") : null;
            int workoutId = jsonObject.getInt("workoutId");

            Workout workout = workoutService.getWorkoutByWorkoutId(workoutId);

            if (workoutService.getWorkoutByWorkoutId(workoutId).getUser() != userService
            .getUserByUsername(userDetails.getUsername()).get()) {
                return ResponseEntity.ok("Not allowed to add to this workout! " + workoutId);
            }

            exercise.setExerciseName(exerciseName);
            exercise.setExerciseDescription(exerciseDescription);
            exercise.setWeight(weight);
            exercise.setRepetition(repetition);
            exercise.setNumberOfSets(numberOfSets);
            exercise.setTime(time);
            exercise.setDistance(distance);
            exercise.setWorkout(workout);

            exerciseService.createExercise(exercise);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());

        }
        return ResponseEntity.ok("Exercise " + 1 + " created!");
    }

    @Operation(summary = "Get all exercises of a workout", 
    parameters = {
        @Parameter(name = "workoutId", 
            description = "Provide workout ID", 
            required = true, 
            example = "9999" 
        )
    } 
)
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = """
                [
                    {
                        "exerciseId": 9979,
                        "exerciseName": "Box Jumps",
                        "exerciseDescription": "Plyometric exercise using a box or platform",
                        "weight": 0,
                        "repetition": 10,
                        "numberOfSets": 3,
                        "time": null,
                        "distance": null,
                        "workout": {
                            "workoutId": 9999,
                            "workoutName": "Morning Workout",
                            "workoutDate": "2024-03-31T22:00:00.000+00:00",
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
                    }
                ]
            """)
        )
    )
    @GetMapping("/all/{workoutId}")
    public ResponseEntity getExercisesOfWorkout(@PathVariable Integer workoutId, @AuthenticationPrincipal UserDetails userDetails) {
            
        Integer id = workoutId;

        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No ID specified!");
            }

            List<Exercise> allExercises = exerciseService.getAllExercisesForWorkout(id);

            if (allExercises.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No workout found or no exercise in workout.");
            }

            if (workoutService.getWorkoutByWorkoutId(id).getUser() != userService
            .getUserByUsername(userDetails.getUsername()).get()) {
                return ResponseEntity.ok("Not allowed to get workout details of this workout! " + id);
        }

            return ResponseEntity.ok(allExercises);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
        }
    }


    @Operation(summary = "Delete exercise", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Provide exercise ID", required = true, content = @Content(
            mediaType = "application/json", examples = @ExampleObject(value = """
                {
                    "exerciseId": 9999
                }
            """)
            )
        )
    )
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = "Deleted exercise 9999!"
            )
        )
    )
    @DeleteMapping("/delete")
    public ResponseEntity deleteExercise(@RequestBody Map<String, Object> json, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            int exerciseId = new JSONObject(json).getInt("exerciseId");
            if(exerciseService.getExercisebyId(exerciseId) == null) {
                return ResponseEntity.ok("No exercise with ID " + exerciseId + ".");
            };

            if (exerciseService.getExercisebyId(exerciseId).getWorkout().getUser() != userService
            .getUserByUsername(userDetails.getUsername()).get()) {
                return ResponseEntity.ok("Not allowed to delete this exercise! " + exerciseId);
            }

            exerciseService.deleteExercise(exerciseId);
            return ResponseEntity.ok("Deleted exercise " + exerciseId + "!");

        } catch (Exception e) {
            return ResponseEntity.status(
                HttpStatus.CONFLICT
                ).body(e.toString());
        }
    }

}
