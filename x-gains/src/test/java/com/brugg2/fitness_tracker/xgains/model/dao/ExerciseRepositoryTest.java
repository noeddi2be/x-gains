package com.brugg2.fitness_tracker.xgains.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.brugg2.fitness_tracker.xgains.model.entity.Exercise;


@DataJpaTest
public class ExerciseRepositoryTest {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Test
    void testFindExercisesByWorkoutId() {

        // Arrange
        Exercise exercise1 = exerciseRepository.getReferenceById(9992);

        int workoutId = 9997;

        // Act
        List<Exercise> exercisesTrue = new ArrayList<>(List.of(exercise1));
        List<Exercise> exercisesAssert = exerciseRepository.findExercisesByWorkoutId(workoutId);

        // Assert if the listed known exercises are considing with 
        // the retrieved exercises from the specific workout.
        assertEquals(exercisesTrue, exercisesAssert);

    }
}
