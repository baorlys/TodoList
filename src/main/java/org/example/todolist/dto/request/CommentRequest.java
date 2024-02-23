package org.example.todolist.dto.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Integer userId;
    private Integer todoListId;
}
