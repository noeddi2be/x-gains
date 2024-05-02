package com.brugg2.fitness_tracker.xgains.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.brugg2.fitness_tracker.xgains.model.entity.User;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindUserByEmail() {

        // Arrange
        String email = "test@example.com";
        int accountType = 0;
        String username = "testuser";
        String firstname = "Test";
        String lastname = "User";

        User user = new User();
        user.setEmail(email);
        user.setAccountType(accountType);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);

        // Save user to the database
        userRepository.save(user);

        // Act
        user = userRepository.findUserByEmail(email);

        // Assert
        assertEquals(email, user.getEmail());

    }
}
