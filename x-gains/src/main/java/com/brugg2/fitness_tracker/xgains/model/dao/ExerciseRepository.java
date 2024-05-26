package com.brugg2.fitness_tracker.xgains.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brugg2.fitness_tracker.xgains.model.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

        /**
         * Abstract method. Handled by spring framework.
         * When the method is called, it executes a manual query.
         * The query is specified in the :userId parameter. JPA supports named
         * parameters -> ":param"
         * 
         * @param userId integer primary key workout.
         * @return java.util.List with Exercise objects.
         */
        @Query(value = "SELECT * FROM exercise " +
                        "WHERE fk_workout_id = :workoutId", nativeQuery = true)
        List<Exercise> findExercisesByWorkoutId(@Param("workoutId") int workoutId);

        /**
         * Method to find exercises by ID.
         * @param exerciseId integer primary key workout.
         * @return Exercise Object
         */
        @Query(value = """
                        SELECT * FROM exercise
                        WHERE exercise.exercise_id = :exerciseId
                        """, nativeQuery = true)
        Exercise findExerciseById(@Param("exerciseId") int exerciseId);
}
