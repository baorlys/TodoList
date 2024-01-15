package org.example.todolist.service;

import org.example.todolist.model.Todolist;

import java.util.List;
import java.util.Optional;

public interface TodoListService {
    Todolist create(Todolist todolist);
    Todolist update(Integer id, Todolist todolist);
    Boolean delete(Integer id);
    Optional<Todolist> getTodoList(Integer id);
    List<Todolist> getAllTodoList();
    List<Todolist> getAllTodoList(Integer userId);
    List<Todolist> getAllTodoList(Integer userId, Integer stateId);
}
