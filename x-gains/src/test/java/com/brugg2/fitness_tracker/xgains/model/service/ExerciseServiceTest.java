package com.brugg2.fitness_tracker.xgains.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brugg2.fitness_tracker.xgains.model.dao.ExerciseRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.Exercise;

@SpringBootTest
public class ExerciseServiceTest {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Test
    void testGetAllExercisesForWorkout() {

        // Arrange
        int workoutId = 9998;

        // Act
        List<Exercise> exercisesAssert = exerciseService.getAllExercisesForWorkout(workoutId);

        // Assert if list of known exercises coinsides with known exercises from
        // testdata.
        assertEquals(3, exercisesAssert.size());
        assertEquals(9996, exercisesAssert.get(0).getExerciseId());
        assertEquals(9995, exercisesAssert.get(1).getExerciseId());
        assertEquals(9994, exercisesAssert.get(2).getExerciseId());

    }

}
