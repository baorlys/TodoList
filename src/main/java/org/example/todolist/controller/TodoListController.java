package org.example.todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.todolist.dto.request.TodoListRequest;
import org.example.todolist.dto.response.TodoListResponse;
import org.example.todolist.model.Task;
import org.example.todolist.model.TodoList;
import org.example.todolist.service.TaskService;
import org.example.todolist.service.TodoListService;
import org.example.todolist.web.ApiError;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo-list")
public class TodoListController {
    @Autowired
    private TodoListService todolistService;
    @Autowired
    private TaskService taskService;
    @GetMapping("/")
    public ResponseEntity<List<?>> getAllTodoList() {
        return ResponseEntity.ok(todolistService.getAllTodoList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<?>> getAllTodoListByUserId(@PathVariable Integer userId ) {
        return ResponseEntity.ok(todolistService.getAllTodoList(userId));
    }

    @GetMapping("/user/{userId}/{stateId}")
    public ResponseEntity<List<?>> getAllTodoListByUserIdAndStateId(@PathVariable Integer userId, @PathVariable Integer stateId ) {
        return ResponseEntity.ok(todolistService.getAllTodoList(userId, stateId));
    }

    @GetMapping("/{todoListId}")
    public ResponseEntity<?> getTodoList(@PathVariable Integer todoListId) {
        return ResponseEntity.ok(todolistService.getTodoListResponse(todoListId));
    }
    @PutMapping("{todoListId}/create-task")
    public ResponseEntity<?> createTask(@PathVariable Integer todoListId) throws Exception {
        try {
            return ResponseEntity.ok(taskService.createWith(todoListId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTodoList(@RequestBody TodoListRequest todolist) {
        return ResponseEntity.ok(todolistService.create(todolist));
    }

    @DeleteMapping("/delete/{todoListId}")
    public ResponseEntity<?> deleteTodoListById(@PathVariable Integer todoListId) {
        return ResponseEntity.ok(todolistService.delete(todoListId));
    }

    @PostMapping("/update/{todoListId}")
    public ResponseEntity<?> updateTodoListById(@PathVariable Integer todoListId, @RequestBody TodoListRequest todoList) throws Exception {
        try {
            return ResponseEntity.ok(todolistService.update(todoListId, todoList));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
