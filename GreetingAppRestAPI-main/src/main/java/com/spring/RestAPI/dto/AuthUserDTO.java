package com.spring.RestAPI.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class AuthUserDTO {

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First name must start with an uppercase letter.")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Last name must start with an uppercase letter.")
    private String lastName;

    @Email(message = "Invalid email format.")
    private String email;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[@#$%^&*()-+=])(?=.*[0-9]).{8,}$",
            message = "Password must contain at least one uppercase letter, one special character, one number, and be at least 8 characters long."
    )
    private String password;
}

