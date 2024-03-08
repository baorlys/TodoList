package org.example.todolist.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProjectResponse {
    private Integer id;
    private String title;
    private Timestamp fromDate;
    private Timestamp toDate;
}
