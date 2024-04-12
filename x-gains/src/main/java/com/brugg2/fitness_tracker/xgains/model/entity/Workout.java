package com.brugg2.fitness_tracker.xgains.model.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout")
public final class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "workout_id")
    private Integer workoutID;

    @Column(name = "workout_name", nullable = false, length = 50)
    private String workoutName;

    @Column(name = "workout_date", nullable = false)
    private Date workoutDate;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_location_id", nullable = false)
    private Location location;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "including", joinColumns = @JoinColumn(name = "fk_workout_id"), inverseJoinColumns = @JoinColumn(name = "fk_exercise_id"))
    private Set<Exercise> exercises;

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

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocation(Location location) {
        this.location = location;
    } 


    // Getters
    public int getWorkoutID() {
        return this.workoutID;
    }

    public String getWorkoutName() {
        return this.workoutName;
    }

    public Date getWorkoutDate() {
        return this.workoutDate;
    }

    public int getDuration() {
        return this.duration;
    }

    public User getUser() {
        return this.user;
    }

    public Location geLocation() {
        return this.location;
    }

}
