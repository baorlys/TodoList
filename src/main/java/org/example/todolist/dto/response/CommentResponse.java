package org.example.todolist.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentResponse {
    private Integer id;
    private String content;
    private UserResponse user;
    private Timestamp createdAt;
}
