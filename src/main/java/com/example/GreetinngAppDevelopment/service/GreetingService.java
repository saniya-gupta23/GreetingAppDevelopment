package com.example.GreetinngAppDevelopment.service;


import com.example.GreetinngAppDevelopment.model.Greeting;
import com.example.GreetinngAppDevelopment.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id).orElse(null);
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
