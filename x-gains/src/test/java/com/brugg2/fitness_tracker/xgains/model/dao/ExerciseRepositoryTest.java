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
    ExerciseRepository exerciseRepository;
    WorkoutRepository workoutRepository;

    @Test
    void testFindExercisesByWorkoutId() {

        Exercise exercise1 = exerciseRepository.getReferenceById(9999);
        Exercise exercise2 = exerciseRepository.getReferenceById(9998);
        Exercise exercise3 = exerciseRepository.getReferenceById(9997);

        int workoutId = 9999;
        List<Exercise> exercisesTrue = new ArrayList<>(List.of(exercise1, exercise2, exercise3));
        List<Exercise> exercisesAssert = exerciseRepository.findExercisesByWorkoutId(workoutId);

        assertEquals(exercisesTrue, exercisesAssert);

    }
}
