package com.spring.RestAPI.service;

import com.spring.RestAPI.model.Greeting;
import com.spring.RestAPI.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // Create Greeting
    public Greeting createGreeting(String firstName, String lastName) {
        String message = generateGreetingMessage(firstName, lastName);
        Greeting greeting = new Greeting(firstName, lastName, message);
        return greetingRepository.save(greeting);
    }

    // Get Greeting by ID
    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    // Get All Greetings
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // Update Greeting
    public Greeting updateGreeting(Long id, String firstName, String lastName) {
        return greetingRepository.findById(id).map(greeting -> {
            greeting.setFirstName(firstName);
            greeting.setLastName(lastName);
            greeting.setMessage(generateGreetingMessage(firstName, lastName));
            return greetingRepository.save(greeting);
        }).orElse(null);
    }

    // Delete Greeting
    public boolean deleteGreeting(Long id) {
        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper method to generate greeting message
    private String generateGreetingMessage(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return "Hello " + firstName + " " + lastName;
        } else if (firstName != null) {
            return "Hello " + firstName;
        } else if (lastName != null) {
            return "Hello " + lastName;
        } else {
            return "Hello World";
        }
    }
}
