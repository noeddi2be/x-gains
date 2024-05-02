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

    @Test
    void testFindUserById() {

        // Arrange
        String email1 = "test2@example.com";
        int accountType = 0;
        String username = "testuser";
        String firstname = "Test";
        String lastname = "User";

        User user2 = new User();
        user2.setEmail(email1);
        user2.setAccountType(accountType);
        user2.setUsername(username);
        user2.setFirstname(firstname);
        user2.setLastname(lastname);

        // Save user to the database
        userRepository.save(user2);
        int userId1 = user2.getUserId();

        // Arrange
        String email2 = "test3@example.com";
        int accountType2 = 0;
        String username2 = "testuser";
        String firstname2 = "Test";
        String lastname2 = "User";

        User user3 = new User();
        user3.setEmail(email2);
        user3.setAccountType(accountType2);
        user3.setUsername(username2);
        user3.setFirstname(firstname2);
        user3.setLastname(lastname2);

        // Save user to the database
        userRepository.save(user3);
        int userId2 = user3.getUserId();

        // Act
        User foundUser1 = new User();
        User foundUser2 = new User();
        
        foundUser1 = userRepository.findUserById(userId1);
        foundUser2 = userRepository.findUserById(userId2);

        // Assert auto generated primary key works
        assertEquals(userId1, userId2 - 1);

        // Assert that found users equal the input users
        assertEquals(email1, foundUser1.getEmail());
        assertEquals(email2, foundUser2.getEmail());
        
    }
}
