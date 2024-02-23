package org.example.todolist.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class TodoListResponse {
    private Integer id;
    private String title;
    private String description;
    private Integer order;
    private StateResponse state;
    private PriorityResponse priority;
    private Integer userId;
    private Timestamp estimation;
    private List<TaskResponse> tasks;
    private List<AssigneeResponse> assignees;

}
