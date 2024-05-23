package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.entity.Exercise;
import com.brugg2.fitness_tracker.xgains.model.service.ExerciseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    /**
     * Method to create a new exercisd using. 
     * Input names of the attributes need to be the java class variable names.
     * 
     * @param exercise is a JSON object and converted to a Java object by Spring.
     * @return Returns the saved object in the database in JSON format.
     */
    @PostMapping("/new")
    public ResponseEntity addExercise(@RequestBody Exercise exercise) {
        try {
            exerciseService.createExercise(exercise);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");

        }
        return ResponseEntity.ok(exercise);
    }

    @GetMapping("/all")
    public ResponseEntity getExercisesOfWorkout(@RequestParam int workoutId) {
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

}
