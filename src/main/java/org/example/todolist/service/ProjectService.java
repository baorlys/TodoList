package org.example.todolist.service;

import org.example.todolist.dto.request.ProjectRequest;
import org.example.todolist.dto.response.ProjectResponse;
import org.example.todolist.dto.response.TodoListResponse;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    ProjectResponse create(ProjectRequest request) throws Exception;
    ProjectResponse update(Integer projectId, ProjectRequest request) throws Exception;
    void delete(Integer projectId) throws Exception;
    Integer hide(Integer projectId) throws Exception;
    List<ProjectResponse> getAll(Integer userId) throws Exception;
    ProjectResponse get(Integer projectId) throws Exception;
    List<TodoListResponse> getTodoListsByProjectId(Integer projectId) throws Exception;
}
