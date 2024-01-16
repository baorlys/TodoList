package org.example.todolist.dto.request;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String title;
    private String description;
    private Integer stateId;
    private Integer priorityId;
    private Integer todolistId;
    private Integer order;
    private Timestamp estimation;
}
