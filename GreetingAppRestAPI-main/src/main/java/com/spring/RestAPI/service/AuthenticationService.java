package com.spring.RestAPI.service;

import com.spring.RestAPI.dto.AuthUserDTO;
import com.spring.RestAPI.dto.LoginDTO;
import com.spring.RestAPI.model.AuthUser;
import com.spring.RestAPI.repository.AuthUserRepository;
import com.spring.RestAPI.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(AuthUserDTO authUserDTO) {
        // Check if email is already registered
        if (authUserRepository.findByEmail(authUserDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use!");
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

    public String login(LoginDTO loginDTO) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(loginDTO.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        AuthUser user = userOptional.get();

        // Verify password
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password!");
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.getEmail());
    }
}
