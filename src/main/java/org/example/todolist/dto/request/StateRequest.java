package org.example.todolist.dto.request;

import lombok.Data;

@Data
public class StateRequest {
    private String title;
    private Integer type;
}
