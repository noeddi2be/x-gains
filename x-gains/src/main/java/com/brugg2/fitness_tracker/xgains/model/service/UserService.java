package com.brugg2.fitness_tracker.xgains.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

}
