package org.example.todolist.dto.request;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProjectRequest {
    private String title;
    private Timestamp fromDate;
    private Timestamp toDate;
    private Integer userId;
}
