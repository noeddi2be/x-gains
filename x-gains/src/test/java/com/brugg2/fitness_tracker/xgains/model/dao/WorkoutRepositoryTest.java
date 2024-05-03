package com.brugg2.fitness_tracker.xgains.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.brugg2.fitness_tracker.xgains.model.entity.Workout;

@DataJpaTest
public class WorkoutRepositoryTest {

    @Autowired
    private WorkoutRepository workoutRepository;
    
    @Test
    void testFindWorkoutsByUserId() {

        // Arrange
        int userid = 9999;
        Workout workout1 = workoutRepository.getReferenceById(9999);
        Workout workout2 = workoutRepository.getReferenceById(9996);

        // Act
        List<Workout> workoutsTrue = new ArrayList<>(List.of(workout2, workout1));
        List<Workout> workoutsAssert = workoutRepository.findWorkoutsByUserId(userid);

        // Assert if retrieved list of workouts equals known list from database.
        assertEquals(workoutsTrue, workoutsAssert);

    }
}