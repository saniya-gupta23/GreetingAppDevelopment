package com.spring.RestAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginDTO {

    @Email(message = "Invalid email format.")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;
}

