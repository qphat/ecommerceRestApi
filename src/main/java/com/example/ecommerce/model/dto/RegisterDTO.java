package com.example.ecommerce.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String Name;

    @NotEmpty(message = "username should not be empty")
    @Size(min = 6, message = "username has at least 6 characters")
    private String username;

    @NotEmpty(message = "email should not be empty")
    private String email;

    @NotEmpty(message = "password should not be empty")
    private String password;

    @NotEmpty(message = "confirm password should not be empty")
    private String confirmPassword;
}
