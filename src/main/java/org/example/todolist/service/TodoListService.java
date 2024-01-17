package org.example.todolist.service;

import org.example.todolist.dto.request.TodoListRequest;
import org.example.todolist.dto.response.TodoListResponse;
import org.example.todolist.model.TodoList;

import java.util.List;

public interface TodoListService {
    TodoListResponse create(TodoListRequest todoListRequest);
    Boolean delete(Integer id);
    List<TodoListResponse> getAllTodoList();
    List<TodoListResponse> getAllTodoList(Integer userId);
    List<TodoListResponse> getAllTodoList(Integer userId, Integer stateId);

    TodoListResponse getTodoListResponse(Integer id);

    TodoListResponse update(Integer id, TodoListRequest todoListRequest) throws Exception;

    TodoListResponse getTodoList(Integer id) throws Exception;
}
