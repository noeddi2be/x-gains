package com.brugg2.fitness_tracker.xgains.model.service;

import java.util.List;

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

    public List<Exercise> getAllExercisesForWorkout(int workoutId) {
        return exerciseRepository.findExercisesByWorkoutId(workoutId);
    }

    public Exercise getExercisebyId(int exerciseId) {
        return exerciseRepository.findExerciseById(exerciseId);
    }

    public void deleteExercise(int exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }

}
