package com.brugg2.fitness_tracker.xgains.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brugg2.fitness_tracker.xgains.model.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    /**
     * Method to return all available locations.
     * @return Locations all objects of location.
     */
    @Query(value = "SELECT * FROM location", nativeQuery = true)
    List<Location> returnAllLocations();

    /**
     * Method to return location by ID.
     * @param locationId integer primary key location.
     * @return Location object.
     */
    @Query(value = """
        SELECT * FROM location
        WHERE location.location_id = :locationId
        """, nativeQuery = true)
    Location findLocationById(int locationId);
}
