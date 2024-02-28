package org.example.todolist.service;

import org.example.todolist.dto.request.AssigneeRequest;
import org.example.todolist.dto.response.AssigneeResponse;
import org.example.todolist.dto.response.TodoListResponse;
import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.model.TodoList;

import java.util.List;

public interface AssigneeService {
    void addAssignee(AssigneeRequest assigneeRequest) throws Exception;
    AssigneeResponse setPermission(AssigneeRequest assigneeRequest) throws Exception;
    void deleteAssignee(Integer todoListId, String email) throws Exception;

    UserResponse getAssigneeInfo(String email) throws Exception;

    List<AssigneeResponse> getAssigneeList(Integer todoListId) throws Exception;

}
