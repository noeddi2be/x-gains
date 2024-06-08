package com.brugg2.fitness_tracker.xgains;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.brugg2.fitness_tracker.xgains.model.dao.UserRepository;
import com.brugg2.fitness_tracker.xgains.model.entity.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class Application {

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Operation(summary = "Endpoint to login", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Basic Auth" 
        )
    )
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = """
                {
                    "auth": "RW15OnwermA3b3Jk",
                    "message": "Welcome to X-Gains! You are logged in as Bob."
                }
            """)
        )
    )
	@GetMapping
    public Map<String, Object> home(Principal principal, HttpSession session, HttpServletRequest request) {
    String message = "Welcome to X-Gains! You are logged in as " + principal.getName() + ".";
    String authorizationHeader = request.getHeader("Authorization");

    Map<String, Object> response = new HashMap<>();
    response.put("message", message);
    response.put("auth", authorizationHeader.substring(6));

    return response;
}

	@Bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            User user = new User();
            user.setAccountType(0);
            user.setUsername("Testuser");
            user.setEmail("testuser@email.com");
            user.setPassword(encoder.encode("password"));
            user.setFirstname("Jane");
            user.setLastname("Doe");
            userRepository.save(user);

            User user2 = userRepository.findUserById(9999);
            if (user2 != null) {
                user2.setPassword(encoder.encode("password"));
                userRepository.save(user2);
            }

            User user3 = userRepository.findUserById(9998);
            if (user3 != null) {
                user3.setPassword(encoder.encode("password"));
                userRepository.save(user3);
            }

            User user4 = userRepository.findUserById(9997);
            if (user4 != null) {
                user4.setPassword(encoder.encode("password"));
                userRepository.save(user4);
            }
        };
	}
	
}
