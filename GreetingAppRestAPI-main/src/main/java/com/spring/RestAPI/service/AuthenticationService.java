package com.spring.RestAPI.service;

import com.spring.RestAPI.dto.AuthUserDTO;
import com.spring.RestAPI.dto.LoginDTO;
import com.spring.RestAPI.model.AuthUser;
import com.spring.RestAPI.repository.AuthUserRepository;
import com.spring.RestAPI.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthenticationService(AuthUserRepository authUserRepository, JwtUtil jwtUtil) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
    }

    public String register(AuthUserDTO authUserDTO) {  // ✅ FIX register(AuthUserDTO)
        // Check if email is already in use
        if (authUserRepository.findByEmail(authUserDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        // Convert DTO to Entity
        AuthUser user = new AuthUser();
        user.setFirstName(authUserDTO.getFirstName());
        user.setLastName(authUserDTO.getLastName());
        user.setEmail(authUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(authUserDTO.getPassword())); // Hash password

        authUserRepository.save(user);
        return "User registered successfully!";
    }

    public String login(LoginDTO loginDTO) {  // ✅ FIX login(LoginDTO)
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(loginDTO.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        AuthUser user = userOptional.get();

        // Verify password
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.getEmail());
    }
}

