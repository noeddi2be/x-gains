package com.brugg2.fitness_tracker.xgains.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
