package com.brugg2.fitness_tracker.xgains.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brugg2.fitness_tracker.xgains.model.dao.LocationRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.Location;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void createLocation(Location location) {
        locationRepository.save(location);
    }

}
