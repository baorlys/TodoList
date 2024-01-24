package org.example.todolist.controller;

import org.example.todolist.dto.request.TodoListRequest;
import org.example.todolist.service.TaskService;
import org.example.todolist.service.TodoListService;
import org.example.todolist.web.ApiSuccess;
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
    public ResponseEntity<List<?>> getAllTodoListByUserIdAndStateId(@PathVariable Integer userId,
                                                                    @PathVariable Integer stateId ) {
        return ResponseEntity.ok(todolistService.getAllTodoList(userId, stateId));
    }

    @GetMapping("/{todoListId}")
    public ResponseEntity<?> getTodoList(@PathVariable Integer todoListId) {
        return ResponseEntity.ok(todolistService.getTodoListResponse(todoListId));
    }
    @PutMapping("{todoListId}/create-task")
    public ResponseEntity<?> createTask(@PathVariable Integer todoListId) throws Exception {
        taskService.createWith(todoListId);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Task created successfully");
        return ResponseEntityBuilder.build(apiSuccess);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTodoList(@RequestBody TodoListRequest todolist) {
        todolistService.create(todolist);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Todo list created successfully");
        return ResponseEntityBuilder.build(apiSuccess);

    }

    @PostMapping("/delete/{todoListId}")
    public ResponseEntity<?> deleteTodoListById(@PathVariable Integer todoListId) throws Exception {
        todolistService.hide(todoListId);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Todo list deleted successfully");
        return ResponseEntityBuilder.build(apiSuccess);
    }

    @PostMapping("/update/{todoListId}")
    public ResponseEntity<?> updateTodoListById(@PathVariable Integer todoListId, @RequestBody TodoListRequest todoList)
            throws Exception {
        todolistService.update(todoListId, todoList);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Todo list updated successfully");
        return ResponseEntityBuilder.build(apiSuccess);
    }


}
