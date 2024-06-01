package com.brugg2.fitness_tracker.xgains.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brugg2.fitness_tracker.xgains.model.dao.ExerciseRepository;
import com.brugg2.fitness_tracker.xgains.model.dao.WorkoutRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.Exercise;

@SpringBootTest
public class ExerciseServiceTest {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    private Exercise setupExercise() {
        Exercise exercise = new Exercise();
        exercise.setExerciseName("Test Exercise");
        exercise.setExerciseDescription("Testing");
        exercise.setWeight(5);
        exercise.setRepetition(5);
        exercise.setNumberOfSets(5);
        exercise.setTime(5);
        exercise.setDistance(5);
        exercise.setWorkout(workoutRepository.getWorkoutById(9997));
        return exercise;
    }

    @Test
    void testCreateExercise() {

        // Arrange
        Exercise exercise = setupExercise();

        // Act
        exerciseService.createExercise(exercise);

        // Assert if exercise was created
        int exerciseId = exercise.getExerciseId();
        assertEquals(5, exerciseRepository.findExerciseById(exerciseId).getTime());
    }

    @Test
    void testGetAllExercisesForWorkout() {

        // Arrange
        int workoutId = 9997;

        // Act
        List<Exercise> exercisesAssert = exerciseService.getAllExercisesForWorkout(workoutId);

        // Assert if list of known exercises coinsides with known exercises from
        // testdata.
        assertEquals(1, exercisesAssert.size());
        assertEquals(9992, exercisesAssert.get(0).getExerciseId());

    }

    @Test
    void testDeleteExercise() {

        // Arrange
        Exercise exercise = setupExercise();

        // Act
        exerciseService.createExercise(exercise);
        int exerciseId = exercise.getExerciseId();

        // Assert if exercise was created
        assertNotNull(exerciseRepository.findExerciseById(exerciseId));

        // Act
        exerciseRepository.deleteById(exerciseId);

        // Assert if exercise was created
        assertNull(exerciseRepository.findExerciseById(exerciseId));

    }

    @Test
    void testGetExercisebyId() {

        // Arrange
        Exercise exercise = setupExercise();

        // Act
        exerciseService.createExercise(exercise);
        int exerciseId = exercise.getExerciseId();

        // Act
        assertEquals(5, exerciseService.getExercisebyId(exerciseId).getTime());
    }

}
