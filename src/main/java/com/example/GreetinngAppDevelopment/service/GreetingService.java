package com.example.GreetinngAppDevelopment.service;


import com.example.GreetinngAppDevelopment.model.Greeting;
import com.example.GreetinngAppDevelopment.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GreetingService {
    @Autowired
    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public String getGreeting(String firstName, String lastName) {
        String greetingMessage;
        if (firstName != null && lastName != null) {
            greetingMessage = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            greetingMessage = "Hello, " + firstName + "!";
        } else if (lastName != null) {
            greetingMessage = "Hello, " + lastName + "!";
        } else {
            greetingMessage = "Hello World!";
        }

        // Save greeting to the database
        Greeting greeting = new Greeting(greetingMessage);
        greetingRepository.save(greeting);

        return greetingMessage;
    }

    public void deleteGreeting(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        if(greeting.isPresent()) {
            greetingRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Greeting not found with ID"+  id);
        }
    }
}
