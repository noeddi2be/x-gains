package com.brugg2.fitness_tracker.xgains;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@SpringBootApplication
@RestController
public class Application {


	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping
	public String home() {
		return "Welcome to X-Gains!";
	}


	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(path="/user")
	public String getMethodName(@RequestParam String param) {
		return "Hello user :)";
	}
	
	@GetMapping(path="/logout")
	public String logoout() {
		return "Successfully logged out!";
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {

			User user = new User();
			user.setAccountType(0);
			user.setUsername("Testuser");
			user.setEmail("testuser@email.com");
			user.setPassword(encoder.encode("password"));
			user.setFirstname("Jane");
			user.setLastname("Doe");

			User user2 = userRepository.findUserById(9999);
			user2.setPassword(encoder.encode("password"));
			
			User user3 = userRepository.findUserById(9998);
			user3.setPassword(encoder.encode("password"));

			User user4 = userRepository.findUserById(9997);
			user4.setPassword(encoder.encode("password"));

			userRepository.save(user);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
		};
	}
	
}
