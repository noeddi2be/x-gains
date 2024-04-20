package com.brugg2.fitness_tracker.xgains.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.dao.WorkoutRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;
import com.brugg2.fitness_tracker.xgains.model.entity.Workout;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    /**
     * Deleting a user and also deleting the workouts with reference to user.
     * @param user User object.
     */
    public void deleteUser(User user) {
        List<Workout> workouts = workoutRepository.findWorkoutsByUserId(user.getUserID());
        for (int i = 0; i < workouts.size(); i++) {
            workoutRepository.delete(workouts.get(i));
        }

        userRepository.delete(user);
    }

}
