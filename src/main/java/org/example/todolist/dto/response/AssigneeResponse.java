package org.example.todolist.dto.response;

import lombok.Data;
import org.example.todolist.enums.PermissionAccess;
@Data
public class AssigneeResponse {
    private UserResponse user;
    private PermissionAccess permission;
}
