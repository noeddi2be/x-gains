package com.brugg2.fitness_tracker.xgains.model.entity;

public class Location {

    private int locationID;
    private String locationName;

    // Setters
    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    // Getters
    public int getLocationID() {
        return this.locationID;
    }

    public String getLocationName() {
        return this.locationName;
    }

}
