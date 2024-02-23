package org.example.todolist.dto.request;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String title;
    private String description;
    private Boolean isCompleted;
    private Integer stateId;
    private Integer priorityId;
    private Integer todolistId;
    private Integer order;
    private Timestamp estimation;
}
