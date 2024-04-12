package com.brugg2.fitness_tracker.xgains.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brugg2.fitness_tracker.xgains.model.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {}
