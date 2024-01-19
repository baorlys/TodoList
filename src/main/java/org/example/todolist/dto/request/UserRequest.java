package org.example.todolist.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Pattern(regexp = "^[a-zA-Z0-9_]{2,30}$", message = "Invalid Name: Must be of 2 - 30 characters")
    private String username;
    @NotBlank(message = "Invalid Email: Empty email")
    @NotNull(message = "Invalid Email: Email is NULL")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Invalid mobile: Empty mobile")
    @NotNull(message = "Invalid mobile: mobile is NULL")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile: Must be of 10 digits")
    private String mobile;
    private Boolean mobileHidden;
}
