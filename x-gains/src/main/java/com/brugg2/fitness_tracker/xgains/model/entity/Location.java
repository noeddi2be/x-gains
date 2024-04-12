package com.brugg2.fitness_tracker.xgains.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public final class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private Integer locationID;

    @Column(name = "location_name", nullable = false, length = 50)
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
