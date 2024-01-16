package org.example.todolist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String email;
    private String mobile;
    private Boolean mobileHidden;
}
