package com.brugg2.fitness_tracker.xgains.model.entity;

import java.util.Date;

public class Workout {

    private int workoutID;
    private String workoutName;
    private Date workoutDate;
    private int duration;

    // Setters
    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }
    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }
    public void setWorkoutDate(Date workoutDate) {
        this.workoutDate = workoutDate;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Getters
    public int getWorkoutID() {
        return workoutID;
    }
    public String getWorkoutName() {
        return workoutName;
    }
    public Date getWorkoutDate() {
        return workoutDate;
    }
    public int getDuration() {
        return duration;
    }

}
