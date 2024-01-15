package org.example.todolist.dto.response;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String username;
    private String password;
}
