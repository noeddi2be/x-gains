package com.brugg2.fitness_tracker.xgains.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brugg2.fitness_tracker.xgains.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
