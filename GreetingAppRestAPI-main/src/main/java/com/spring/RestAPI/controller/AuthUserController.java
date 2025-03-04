package com.spring.RestAPI.controller;

import com.spring.RestAPI.dto.AuthUserDTO;
import com.spring.RestAPI.dto.LoginDTO;
import com.spring.RestAPI.service.AuthenticationService;
import com.spring.RestAPI.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthUserController {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;  // ✅ Inject EmailService

    public AuthUserController(AuthenticationService authenticationService, EmailService emailService) {
        this.authenticationService = authenticationService;
        this.emailService = emailService;  // ✅ Assign EmailService
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthUserDTO userDTO) {
        try {
            String response = authenticationService.register(userDTO);
            return ResponseEntity.status(201).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            String token = authenticationService.login(loginDTO);
            return ResponseEntity.ok().body("{\"message\": \"Login successful!\", \"token\": \"" + token + "\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendTestEmail(@RequestParam String to) {
        try {
            emailService.sendEmail(to, "Test Email", "This is a test email from Spring Boot.");
            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send email: " + e.getMessage());
        }
    }
}
