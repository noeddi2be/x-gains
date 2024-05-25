package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.entity.Location;
import com.brugg2.fitness_tracker.xgains.model.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     * Method to create a new location.
     * Input names of the attributes need to be the java class variable names.
     * 
     * @param location is a JSON object and converted to a Java object by Spring.
     * @return Returns the saved object in the database in JSON format.
     */
    @PostMapping("/new")
    public ResponseEntity addLocation(@RequestBody Location location) {
        try {
            locationService.createLocation(location);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");

        }
        return ResponseEntity.ok(location);
    }

    /**
     * Method to return locations.
     * 
     * @return Returns a List object with all locations.
     */
    @GetMapping("all")
    public ResponseEntity getAllLocations() {
        try {
            return ResponseEntity.ok(locationService.returnAllLocations());
        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");
        }
    }

}
