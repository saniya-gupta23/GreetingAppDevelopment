package com.example.GreetinngAppDevelopment.service;


import com.example.GreetinngAppDevelopment.model.Greeting;
import com.example.GreetinngAppDevelopment.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

    public Greeting createGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

//    public String getGreeting() {
//        return "Hello World";
//    }

    public Greeting updateGreeting(Long id, String message) {
        Optional<Greeting> optionalGreeting = greetingRepository.findById(id);
        if (optionalGreeting.isPresent()) {
            Greeting greeting = optionalGreeting.get();
            greeting.setMessage(message);
            return greetingRepository.save(greeting);
        } else {
            throw new RuntimeException("Greeting not found with id: " + id);
        }
    }

    public void deleteGreeting(Long id) {
        greetingRepository.deleteById(id);
    }
}
