package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.entity.Exercise;
import com.brugg2.fitness_tracker.xgains.model.entity.Workout;
import com.brugg2.fitness_tracker.xgains.model.service.ExerciseService;
import com.brugg2.fitness_tracker.xgains.model.service.WorkoutService;

import java.util.List;

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
@RequestMapping("/api/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired 
    private WorkoutService workoutService;

    /**
     * Method to create a new exercisd using. 
     * Input names of the attributes need to be the java class variable names.
     * 
     * @param exercise is a JSON object and converted to a Java object by Spring.
     * @return Returns the saved object in the database in JSON format.
     */
    @PostMapping("/new")
    public ResponseEntity addExercise(@RequestBody String json) {

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
        return ResponseEntity.ok(exercise);
    }

    @GetMapping("/all")
    public ResponseEntity getExercisesOfWorkout(@RequestBody String json) {
            
        JSONObject jsonObject = new JSONObject(json);
        Integer workoutId = jsonObject.getInt("workoutId");

        try {
            List<Exercise> allExercises = exerciseService.getAllExercisesForWorkout(workoutId);

            if (allExercises.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found");
            }

            return ResponseEntity.ok(allExercises);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteExercise(@RequestBody String json) {
        try {
            int exerciseId = new JSONObject(json).getInt("exerciseId");
            if(exerciseService.getExercisebyId(exerciseId) == null) {
                return ResponseEntity.ok("No exercise with ID " + exerciseId + ".");
            };

            exerciseService.deleteExercise(exerciseId);
            return ResponseEntity.ok("Deletion successful!");

        } catch (Exception e) {
            return ResponseEntity.status(
                HttpStatus.CONFLICT
                ).body(e.toString());
        }
    }

}
