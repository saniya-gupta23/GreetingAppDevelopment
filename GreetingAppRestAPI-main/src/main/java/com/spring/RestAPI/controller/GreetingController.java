package com.spring.RestAPI.controller;

import com.spring.RestAPI.model.Greeting;
import com.spring.RestAPI.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // Create Greeting
    @PostMapping
    public Greeting createGreeting(@RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName) {
        return greetingService.createGreeting(firstName, lastName);
    }

    // Get Greeting by ID
    @GetMapping("/{id}")
    public Optional<Greeting> getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }

    // Get All Greetings
    @GetMapping
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // Edit Greeting
    @PutMapping("/{id}")
    public Greeting updateGreeting(@PathVariable Long id,
                                   @RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName) {
        return greetingService.updateGreeting(id, firstName, lastName);
    }

    // Delete Greeting
    @DeleteMapping("/{id}")
    public String deleteGreeting(@PathVariable Long id) {
        return greetingService.deleteGreeting(id) ? "Greeting deleted successfully" : "Greeting not found";
    }


}
