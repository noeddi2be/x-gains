package com.brugg2.fitness_tracker.xgains;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64;

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

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class Application {


	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Operation(summary = "Login", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Basic Auth" 
        )
    )
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
        mediaType = "application/json", examples = @ExampleObject(
            value = """
                {
                    "Message": "Welcome to X-Gains! You are logged in as Bob.",
                    "Base64" : "Qm9iOkhpLCB5b3UgYXJlIGEgbmVyZCE="
                  }
            """)
        )
    )
	@GetMapping
    public Map<String, String> home(Principal principal, HttpSession session) {
    String sessionId = session.getId(); // Retrieve the JSESSIONID
    String base64SessionId = Base64.getEncoder().encodeToString(sessionId.getBytes());

    String message = "Welcome to X-Gains! You are logged in as " + principal.getName() + ".";

    Map<String, String> response = new HashMap<>();
    response.put("message", message);
    response.put("cookie", base64SessionId);

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
