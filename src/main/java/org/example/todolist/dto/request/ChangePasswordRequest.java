package org.example.todolist.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotBlank(message = "Invalid Email: Empty email")
    @NotNull(message = "Invalid Email: Email is NULL")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Invalid Password: Empty password")
    @NotNull(message = "Invalid Password: Password is NULL")
    @Size(min = 8, max = 30, message = "Invalid Password: Must be of 8 - 30 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Invalid Password: Must contain at least " +
            "1 uppercase, 1 lowercase and 1 number")
    private String oldPassword;
    @NotBlank(message = "Invalid Password: Empty password")
    @NotNull(message = "Invalid Password: Password is NULL")
    @Size(min = 8, max = 30, message = "Invalid Password: Must be of 8 - 30 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Invalid Password: Must contain at least " +
            "1 uppercase, 1 lowercase and 1 number")
    private String newPassword;
}
