package org.example.todolist.dto.request;

import lombok.Data;
import org.example.todolist.enums.PermissionAccess;
@Data
public class AssigneeRequest {
    private Integer todoListId;
    private String email;
    private Integer permissionId;
}
