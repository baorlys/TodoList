package org.example.todolist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdate {
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Boolean mobile_hidden;
}
