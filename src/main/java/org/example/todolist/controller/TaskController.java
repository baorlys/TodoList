package org.example.todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.todolist.dto.request.AssigneeRequest;
import org.example.todolist.dto.request.CommentRequest;
import org.example.todolist.dto.request.TaskRequest;
import org.example.todolist.model.Task;
import org.example.todolist.service.AssigneeService;
import org.example.todolist.service.CommentService;
import org.example.todolist.service.TaskService;
import org.example.todolist.web.ApiError;
import org.example.todolist.web.ApiSuccess;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private AssigneeService assigneeService;
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Integer taskId) throws Exception {
        return ResponseEntity.ok(taskService.find(taskId));
    }
    @GetMapping("/{todoListId}/tasks")
    public ResponseEntity<?> getAllTaskByTodoListId(@PathVariable Integer todoListId) {
        return ResponseEntity.ok(taskService.getAllByTodolist(todoListId));
    }
    @PutMapping("{todoListId}/create-task")
    public ResponseEntity<?> createTask(@PathVariable Integer todoListId) throws Exception {
        taskService.createWith(todoListId);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Task created successfully");
        return ResponseEntityBuilder.build(apiSuccess);
    }

    @PostMapping("/update/{taskId}")
    public ResponseEntity<?> updateTaskById(@PathVariable Integer taskId, @RequestBody TaskRequest taskRequest) throws Exception {
        taskService.update(taskId, taskRequest);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Task updated successfully");
        return ResponseEntityBuilder.build(apiSuccess);
    }

    @PostMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Integer taskId) throws Exception {
        taskService.hide(taskId);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Task deleted successfully");
        return ResponseEntityBuilder.build(apiSuccess);
    }






}
