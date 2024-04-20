package com.brugg2.fitness_tracker.xgains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class Application {


	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping(path="/")
	public String getWelcomeString() {
		return "Welcome to X-Gains!";
	}
	
}
