package com.spring.RestAPI.controller;
//package com.spring.RestAPI.controller;

import com.spring.RestAPI.dto.AuthUserDTO;
import com.spring.RestAPI.dto.LoginDTO;
import com.spring.RestAPI.service.AuthUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    private final AuthUserService authService;

    public AuthUserController(AuthUserService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthUserDTO userDTO) {
        return authService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return authService.loginUser(loginDTO);
    }
}