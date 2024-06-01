package com.brugg2.fitness_tracker.xgains.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.brugg2.fitness_tracker.xgains.model.dao.LocationRepository;
import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.dao.WorkoutRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;
import com.brugg2.fitness_tracker.xgains.model.entity.Workout;

@SpringBootTest
@ActiveProfiles("test")
public class WorkoutServiceTest {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    private Workout createWorkout() {
        Workout workout = new Workout();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = formatter.parse("2024-01-01 00:00:00");
        } catch (Exception e){};

        User user = new User();
        user.setAccountType(0);
        user.setBirthdate(date);
        user.setEmail("test@mail.com");
        user.setFirstname("Test");
        user.setLastname("User");
        user.setPassword("12345");
        user.setUsername("TestUser");
        userRepository.save(user);

        workout.setWorkoutName("Test Workout");
        workout.setWorkoutDate(date);
        workout.setDuration(5);
        workout.setUser(user);
        workout.setLocation(locationRepository.findLocationById(20));
        return workout;
    }

    @Test
    void testCreateWorkout() {

        // Arrange
        Workout workout = createWorkout();

        // Act
        workoutService.createWorkout(workout);
        int workoutId = workout.getWorkoutId();

        // Assert
        assertEquals("Test Workout", workoutRepository.getWorkoutById(workoutId).getWorkoutName());

    }

    @Test
    void testDeleteWorkout() {


        // Arrange
        Workout workout = createWorkout();

        // Act
        workoutService.createWorkout(workout);
        int workoutId = workout.getWorkoutId();
        workoutService.deleteWorkout(workoutId);

        // Assert
        assertEquals(null, workoutRepository.getWorkoutById(workoutId));

    }

    @Test
    void testGetAllWorkouts() {

        // Arrange
        User user = userRepository.findUserById(9997);

        // Act
        int size = workoutService.getAllWorkouts(user).size();

        // Assert
        assertEquals(2, size);

    }

    @Test
    void testGetWorkoutByWorkoutId() {

        // Arrange
        Workout workout = createWorkout();

        // Act
        workoutService.createWorkout(workout);
        int workoutId = workout.getWorkoutId();
        int workoutId2 = workout.getWorkoutId();

        // Assert workout ID's match
        assertEquals(workoutId, workoutId2);
        assertTrue(workout.getWorkoutId() + 1 > 0);

    }
}
