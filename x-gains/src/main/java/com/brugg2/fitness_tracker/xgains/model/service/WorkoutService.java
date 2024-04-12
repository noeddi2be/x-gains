package com.brugg2.fitness_tracker.xgains.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brugg2.fitness_tracker.xgains.model.dao.WorkoutRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.Workout;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    public void createWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

}
