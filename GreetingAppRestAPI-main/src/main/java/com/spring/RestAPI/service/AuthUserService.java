package com.spring.RestAPI.service;


import com.spring.RestAPI.dto.AuthUserDTO;
import com.spring.RestAPI.dto.LoginDTO;
import com.spring.RestAPI.model.AuthUser;
import com.spring.RestAPI.repository.AuthUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    private final AuthUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthUserService(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String registerUser(AuthUserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "Email is already in use.";
        }

        // Use parameterized constructor
        AuthUser user = new AuthUser();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password

        userRepository.save(user);
        return "User registered successfully!";
    }


    public String loginUser(LoginDTO loginDTO) {
        AuthUser user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return "Invalid email or password!";
        }

        return "Login successful!";
    }
}