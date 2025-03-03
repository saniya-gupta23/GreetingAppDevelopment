package com.example.GreetinngAppDevelopment.controller;

import com.example.GreetinngAppDevelopment.model.Greeting;
import com.example.GreetinngAppDevelopment.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable Long id) {
        greetingService.deleteGreeting(id);
        return ResponseEntity.ok("Greeting deleted successfully");
    }


    @PostMapping
    public ResponseEntity<String> createdGreeting(@RequestBody Greeting greeting) {
        Greeting createdGreeting = greetingService.createGreeting(greeting.getMessage());
        return ResponseEntity.ok("greeting created successfully with id:"+createdGreeting.getId());
    }




    // Update Greeting by ID (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Greeting> updateGreeting(@PathVariable Long id, @RequestBody Greeting greeting) {
        Greeting updatedGreeting = greetingService.updateGreeting(id, greeting.getMessage());
        return ResponseEntity.ok(updatedGreeting);
    }



}