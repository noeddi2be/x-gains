package com.brugg2.fitness_tracker.xgains.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private User user;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {

        // Arrange
        user.setAccountType(0);
        user.setUsername("testuser");
        user.setEmail("testthisisunique.user@gmail.com");
        user.setPassword("password");
        user.setFirstname("Test");
        user.setLastname("User");
        Date birthdate = new Date(1990-01-01);
        user.setBirthdate(birthdate);

        // Act
        userService.createUser(user);

        // Assert if user is not null and email is matching.
        assertNotNull(userRepository.findUserByEmail("testthisisunique.user@gmail.com"));

    }

    @Test
    void testDeleteUser() {

        // Arrange
        User userToDelete = userRepository.getReferenceById(9999);

        // Act
        userService.deleteUser(userToDelete);

        // Assert
        assertNull(userService.getUserByEmail(userToDelete.getEmail()));



    }

    @Test
    void testGetUserByEmail() {

        // Arrange
        User userMustExist = userService.getUserByEmail("emily.sharp@gmail.com");

        // Assert user exists
        assertNotNull(userMustExist);

    }

    @Test
    void testGetUserById() {

        // Arrange
        User userMustExist = userService.getUserById(9999);

        // Assert user exists
        assertNotNull(userMustExist);

    }
}
