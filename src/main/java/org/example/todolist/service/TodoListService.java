package org.example.todolist.service;

import org.example.todolist.dto.request.TodoListRequest;
import org.example.todolist.dto.response.TodoListResponse;
import org.example.todolist.model.TodoList;

import java.util.List;

public interface TodoListService {
    TodoListResponse create(TodoListRequest todoListRequest) throws Exception;
    Boolean delete(Integer id) throws Exception;
    Boolean hide(Integer id) throws Exception;
    List<TodoListResponse> getAllTodoList() throws Exception;
    List<TodoListResponse> getAllTodoList(Integer userId) throws Exception;
    List<TodoListResponse> getAllTodoList(Integer userId, Integer stateId) throws Exception;

    TodoListResponse getTodoListResponse(Integer id);

    TodoListResponse update(Integer id, TodoListRequest todoListRequest) throws Exception;

    public List<TodoListResponse> getAllTodoListAssignee(Integer userId) throws Exception;

    TodoListResponse getTodoList(Integer id) throws Exception;
}
