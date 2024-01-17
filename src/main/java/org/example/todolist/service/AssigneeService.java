package org.example.todolist.service;

import org.example.todolist.dto.request.AssigneeRequest;
import org.example.todolist.dto.response.AssigneeResponse;
import org.example.todolist.dto.response.UserResponse;
import org.springframework.data.jpa.repository.Query;

public interface AssigneeService {
    AssigneeResponse addAssignee(AssigneeRequest assigneeRequest) throws Exception;
    AssigneeResponse setPermission(AssigneeRequest assigneeRequest) throws Exception;
    void deleteAssignee(Integer taskId, String email) throws Exception;

    UserResponse getAssigneeInfo(String email) throws Exception;
}
