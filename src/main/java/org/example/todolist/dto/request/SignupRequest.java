package org.example.todolist.dto.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String username;
}
