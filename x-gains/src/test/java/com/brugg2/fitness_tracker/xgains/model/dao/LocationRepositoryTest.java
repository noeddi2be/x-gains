package com.brugg2.fitness_tracker.xgains.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.brugg2.fitness_tracker.xgains.model.entity.Location;

@SpringBootTest
@ActiveProfiles("test")
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void testFindLocationById() {

        // Act
        Location location = new Location();

        // Arrange
        location = locationRepository.findLocationById(20);

        // Assert
        assertEquals(20, location.getLocationId());

    }

    @Test
    void testReturnAllLocations() {

        // Act
        Integer numberOfLocations = locationRepository.returnAllLocations().size();

        // Assert
        assertNotNull(numberOfLocations);
    }

    @Test
    void testFindLocationByName() {

        // Act
        Location location = new Location();
        String locationName = "Brugg";
        
        // Arrange
        location = locationRepository.findLocationByName(locationName);

        // Assert
        assertEquals(locationName, location.getLocationName());
    }
}
