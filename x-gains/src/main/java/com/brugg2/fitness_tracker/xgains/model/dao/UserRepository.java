package com.brugg2.fitness_tracker.xgains.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brugg2.fitness_tracker.xgains.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Method to find a user by ID. 
     * @param userId integer primary key user.
     * @return User object of the corresponding user.
     */
    @Query(value = "SELECT * FROM account " +
            "WHERE account.user_id = :userId", nativeQuery = true)
    public User findUserById(int userId);

    /**
     * Method to find user by email. 
     * @param email String email address of user. 
     * @return User object of the corresponding user.
     */
    @Query(value = "SELECT * FROM account " +
            "WHERE account.email = :email", nativeQuery = true)
    public User findUserByEmail(String email);

}
