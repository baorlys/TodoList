package org.example.todolist.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TodoListRequest {
    private String title;
    private String description;
    private Integer stateId;
    private Integer priorityId;
    private Integer order;
    private Timestamp estimation;
}
