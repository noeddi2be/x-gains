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

    /**
     * Deleting a user and also deleting the workouts with reference to user.
     * @param user User object.
     */
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserById(int userId) {
        return userRepository.findUserById(userId);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}
