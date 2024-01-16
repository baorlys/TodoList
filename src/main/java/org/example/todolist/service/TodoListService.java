package org.example.todolist.service;

import org.example.todolist.dto.request.TodoListRequest;
import org.example.todolist.dto.response.TodoListResponse;
import org.example.todolist.model.TodoList;

import java.util.List;

public interface TodoListService {
    TodoList create(TodoList todolist);
    Boolean delete(Integer id);
    List<TodoList> getAllTodoList();
    List<TodoList> getAllTodoList(Integer userId);
    List<TodoList> getAllTodoList(Integer userId, Integer stateId);

    TodoListResponse getTodoListResponse(Integer id);

    TodoListResponse update(Integer id, TodoListRequest todoListRequest) throws Exception;

    TodoList getTodolist(Integer id);
}
