package org.example.todolist.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {

    private String email;

    private String password;

    private String username;
}
