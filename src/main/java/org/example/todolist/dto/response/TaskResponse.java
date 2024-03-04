package org.example.todolist.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskResponse {
    private Integer id;
    private String title;
    private String description;
    private Boolean isCompleted;
    private StateResponse state;
    private PriorityResponse priority;
    private Integer order;
    private Timestamp estimation;
}
