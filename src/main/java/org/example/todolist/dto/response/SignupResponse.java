package org.example.todolist.dto.response;

import lombok.Data;

@Data
public class SignupResponse {
    private Integer id;
    private String email;
    private String username;
}
