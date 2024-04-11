package com.brugg2.fitness_tracker.xgains.model.entity;

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
    private int exerciseID;

    @Column(name = "exercise_name", nullable = false, length = 50)
    private String exerciseName;

    @Column(name = "exercise_description", nullable = false, length = 255)
    private String exerciseDescription;

    @Column(name = "weight")
    private int weight;

    @Column(name = "repetition")
    private int repetition;

    @Column(name = "number_of_sets")
    private int numberOfSets;

    @Column(name = "time")
    private int time;

    @Column(name = "distance")
    private int distance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // Setters
    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public void setNumberOfSets(int numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // Getters
    public int getExerciseID() {
        return this.exerciseID;
    }

    public String getExerciseName() {
        return this.exerciseName;
    }

    public String getExerciseDescription() {
        return this.exerciseDescription;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getRepetition() {
        return this.repetition;
    }

    public int getNumberOfSets() {
        return this.numberOfSets;
    }

    public int getTime() {
        return this.time;
    }

    public int getDistance() {
        return this.distance;
    }

    public User getUser() {
        return this.user;
    }

}
