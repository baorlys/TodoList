package org.example.todolist.dto.request;

import lombok.Data;
@Data
public class AssigneeRequest {
    private Integer todoListId;
    private String email;
    private Integer permissionId;
}
