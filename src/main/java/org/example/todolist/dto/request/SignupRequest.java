package org.example.todolist.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Invalid Email: Empty email")
    @NotNull(message = "Invalid Email: Email is NULL")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Invalid Password: Empty password")
    @NotNull(message = "Invalid Password: Password is NULL")
    @Size(min = 8, max = 30, message = "Invalid Password: Must be more than 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Invalid Password: Must contain at least " +
            "1 uppercase, 1 lowercase and 1 number")
    private String password;
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 2, max = 30, message = "Invalid Name: Must be of 2 - 30 characters")
    private String username;
}
