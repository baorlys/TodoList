package org.example.todolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
}
