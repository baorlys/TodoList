package org.example.todolist.service;

import org.example.todolist.dto.request.TaskRequest;
import org.example.todolist.dto.response.TaskResponse;
import org.example.todolist.model.TodoList;

import java.util.List;


public interface TaskService {
    List<TaskResponse> getAllByTodolist(Integer todoListId);
    List<TaskResponse> getAllByTodolist(TodoList todoList);


    List<TaskResponse> getAllByTodolistAndState(TodoList todolist, Integer stateId);

    TaskResponse find(Integer id) throws Exception;


    TaskResponse createWith(Integer todolistId) throws Exception;

    TaskResponse update(Integer id, TaskRequest taskRequest) throws Exception;

    Boolean delete(Integer id) throws Exception;

    Boolean hide(Integer id) throws Exception;
}
