package org.example.todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.todolist.dto.request.TaskRequest;
import org.example.todolist.model.Task;
import org.example.todolist.service.TaskService;
import org.example.todolist.web.ApiError;
import org.example.todolist.web.ApiSuccess;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Integer taskId) throws Exception {
        try {
            return ResponseEntity.ok(taskService.find(taskId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/update/{taskId}")
    public ResponseEntity<?> updateTaskById(@PathVariable Integer taskId, @RequestBody TaskRequest taskRequest) throws Exception {
        try {
            return ResponseEntity.ok(taskService.update(taskId, taskRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Integer taskId) throws Exception {
        try {
            taskService.delete(taskId);
            ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Task deleted successfully");
            return ResponseEntityBuilder.build(apiSuccess);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getServletPath());
        return ResponseEntityBuilder.build(apiError);
    }
}
