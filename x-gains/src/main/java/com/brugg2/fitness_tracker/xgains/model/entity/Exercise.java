package com.brugg2.fitness_tracker.xgains.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise")
public final class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exercise_id")
    private Integer exerciseId;

    @Column(name = "exercise_name", nullable = false, length = 50)
    private String exerciseName;

    @Column(name = "exercise_description", nullable = false, length = 255)
    private String exerciseDescription;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "repetition")
    private Integer repetition;

    @Column(name = "number_of_sets")
    private Integer numberOfSets;

    @Column(name = "time")
    private Integer time;

    @Column(name = "distance")
    private Integer distance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_workout_id", nullable = false)
    private Workout workout;


    // Setters
    public void setExerciseID(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }

    public void setNumberOfSets(Integer numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }


    // Getters
    public int getExerciseId() {
        return this.exerciseId;
    }

    public String getExerciseName() {
        return this.exerciseName;
    }

    public String getExerciseDescription() {
        return this.exerciseDescription;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public Integer getRepetition() {
        return this.repetition;
    }

    public Integer getNumberOfSets() {
        return this.numberOfSets;
    }

    public Integer getTime() {
        return this.time;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public Workout getWorkout() {
        return workout;
    }

}
