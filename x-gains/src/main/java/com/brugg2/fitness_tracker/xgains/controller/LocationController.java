package com.brugg2.fitness_tracker.xgains.controller;

import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.entity.Location;
import com.brugg2.fitness_tracker.xgains.model.service.LocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    @Operation(summary = "Add a new location", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "New location details", required = true, content = @Content(
            mediaType = "application/json", examples = @ExampleObject(value = """
                {
                    "locationName": "Zürich"
                }
            """)
            )
        )
    )
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = "Location Zürich added!"
            )
        )
    )
    @PostMapping("/new")
    public ResponseEntity addLocation(@RequestBody Location location) {
        try {
            locationService.createLocation(location);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");

        }
        return ResponseEntity.ok("Location " + location.getLocationName() + " added!");
    }

     /**
     * Method to return locations.
     * 
     * @return Returns a List object with all locations.
     */
    @Operation(summary = "Get all available locations")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = """
                [
                    {
                        "locationId": 1,
                        "locationName": "Zürich"
                    },
                    {
                        "locationId": 18,
                        "locationName": "Olten"
                    },
                    {
                        "locationId": 19,
                        "locationName": "Basel"
                    },
                    {
                        "locationId": 20,
                        "locationName": "Brugg"
                    }
                ]
            """)
        )
    )
    @GetMapping("all")
    public ResponseEntity getAllLocations() {
        try {
            return ResponseEntity.ok(locationService.returnAllLocations());
        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");
        }
    }


}
