package com.brugg2.fitness_tracker.xgains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brugg2.fitness_tracker.xgains.model.service.UserService;

@SpringBootApplication
public class Application {


	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
