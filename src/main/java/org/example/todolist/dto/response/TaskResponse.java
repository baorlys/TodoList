package org.example.todolist.dto.response;

import lombok.Data;
import org.example.todolist.model.Priority;
import org.example.todolist.model.State;

import java.sql.Timestamp;
import java.util.List;

@Data
public class TaskResponse {
    private Integer id;
    private String title;
    private String description;
    private StateResponse state;
    private PriorityResponse priority;
    private List<AssigneeResponse> assignees;
    private Integer order;
    private Timestamp estimation;
}
