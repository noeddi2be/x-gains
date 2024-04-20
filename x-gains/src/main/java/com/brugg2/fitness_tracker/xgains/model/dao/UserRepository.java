package com.brugg2.fitness_tracker.xgains.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brugg2.fitness_tracker.xgains.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Abstract method. Handled by Spring framework. 
     * When the method is called, it executes a manual query.
     * The query is specified in the :userId parameter. JPA supports named
     * parameters -> ":param"
     * 
     * @param userId integer primary key user.
     * @return User object of the corresponding user.
     */
    @Query(value = "SELECT * FROM account " +
            "WHERE account.user_id = :userId", nativeQuery = true)
    public User findUserById(int userId);

}
