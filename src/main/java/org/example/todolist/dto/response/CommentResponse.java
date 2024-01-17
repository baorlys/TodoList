package org.example.todolist.dto.response;

import lombok.Data;

@Data
public class CommentResponse {
    private String content;
    private UserResponse user;
}
