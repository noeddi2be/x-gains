package com.brugg2.fitness_tracker.xgains.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brugg2.fitness_tracker.xgains.model.entity.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    /**
     * Method to find all workouts of a user.
     * @param userId integer primary key user.
     * @return java.util.List with Workout objects.
     */
    @Query(value = """
            SELECT * FROM account
            LEFT JOIN workout ON account.user_id = workout.fk_user_id
            WHERE account.user_id = :userId
            """, nativeQuery = true)
    List<Workout> findWorkoutsByUserId(@Param("userId") int userId);

    /**
     * Method to get workout by ID.
     * @param workoutId integer primary key workout.
     * @return Workout object.
     */
    @Query(value = """
        SELECT * FROM workout
        WHERE workout_id = :workoutId
            """, nativeQuery = true)
    Workout getWorkoutById(@Param("workoutId") int workoutId);

    /**
     * Method to get workout by name.
     * @param workoutName name of workout.
     * @return Workout object.
     */
    @Query(value = """
        SELECT * FROM workout
        WHERE workout_name = :workoutName
            """, nativeQuery = true)
    Workout findWorkoutByName(@Param("workoutName") String workoutName);
}
