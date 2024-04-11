package com.brugg2.fitness_tracker.xgains.model.entity;

public final class Exercise {

    private int exerciseID;
    private String exerciseName;
    private String exerciseDescription;
    private int weight;
    private int repetition;
    private int numberOfSets;
    private int time;
    private int distance;

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


    // Getters
    public int getExerciseID() {
        return exerciseID;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public int getWeight() {
        return weight;
    }

    public int getRepetition() {
        return repetition;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public int getTime() {
        return time;
    }

    public int getDistance() {
        return distance;
    }

}
