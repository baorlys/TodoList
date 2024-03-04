package org.example.todolist.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TodoListRequest {
    private String title;
    private String description;
    private Integer typeId;
    private Integer priorityId;
    private Integer order;
    private Timestamp estimation;
    private Integer userId;
}
