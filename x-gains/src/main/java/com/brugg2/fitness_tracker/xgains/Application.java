package com.brugg2.fitness_tracker.xgains;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brugg2.fitness_tracker.xgains.model.entity.User;
import com.brugg2.fitness_tracker.xgains.model.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Application {


	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void initTestData() throws Exception {
        User user = new User();
        user.setAccountType(0);
        user.setUsername("testusername");
        user.setEmail("test@mail");
        user.setSalt();
        user.setPassword("12345");
        user.setFirstname("Firstname");
        user.setLastname("Lastname");
        Date date = new Date(0);
        user.setBirthdate(date);

        userService.createUser(user);
    }
}
