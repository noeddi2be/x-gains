package com.brugg2.fitness_tracker.xgains.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brugg2.fitness_tracker.xgains.model.dao.ExerciseRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.Exercise;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public void createExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

}
