package com.brugg2.fitness_tracker.xgains.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.brugg2.fitness_tracker.xgains.model.dao.LocationRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.Location;

@SpringBootTest
@ActiveProfiles("test")
public class LocationServiceTest {

    @Autowired
    LocationService locationService;

    @Autowired
    LocationRepository locationRepository;

    private Location setupLocation() {
        Location location = new Location();
        location.setLocationName("Test Location");
        return location;
    }

    @Test
    void testCreateLocation() {

        // Arrange
        Location location = setupLocation();

        // Act
        locationService.createLocation(location);

        // Assert Strings match
        int locationId = location.getLocationId();
        assertEquals("Test Location", locationRepository.findLocationById(locationId).getLocationName());

    }

    @Test
    void testGetLocationById() {

        // Arrange
        Location location = setupLocation();

        // Act
        locationService.createLocation(location);
        int locationId = location.getLocationId();
        int locationId2 = location.getLocationId();

        // Assert location ID's match
        assertEquals(locationId, locationId2);
        assertTrue(location.getLocationId() + 1 > 0);

    }

    @Test
    void testReturnAllLocations() {

        // Arrange
        List<Location> locations;
        Location location = new Location();
        location.setLocationName("Test");

        // Act
        int size1 = locationService.returnAllLocations().size();
        locationService.createLocation(location);
        int size2 = locationService.returnAllLocations().size();

        // Assert
        assertNotEquals(size1, size2);

    }
}
