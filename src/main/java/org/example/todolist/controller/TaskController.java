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

    @PostMapping("/{taskId}/add-assignee")
    public ResponseEntity<?> addAssignee(@PathVariable Integer taskId, @RequestBody AssigneeRequest assigneeRequest) throws Exception {
        try {
            assigneeRequest.setTaskId(taskId);
            return ResponseEntity.ok(assigneeService.addAssignee(assigneeRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/{taskId}/delete-assignee")
    public ResponseEntity<?> delete(@PathVariable Integer taskId, @RequestBody AssigneeRequest assigneeRequest) throws Exception {
        try {
            assigneeRequest.setTaskId(taskId);
            return ResponseEntity.ok(assigneeService.addAssignee(assigneeRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{taskId}/get-comments")
    public ResponseEntity<List<?>> getComments(@PathVariable Integer taskId) throws Exception {
        try {
            return ResponseEntity.ok(commentService.getComments(taskId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/{taskId}/add-comment")
    public ResponseEntity<?> addComment(@PathVariable Integer taskId, @RequestBody CommentRequest commentRequest) throws Exception {
        try {
            commentRequest.setTaskId(taskId);
            commentService.addComment(commentRequest);
            ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Comment added successfully");
            return ResponseEntityBuilder.build(apiSuccess);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/{taskId}/delete-comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer taskId, @PathVariable Integer commentId) throws Exception {
        try {
            commentService.deleteComment(commentId);
            ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Comment deleted successfully");
            return ResponseEntityBuilder.build(apiSuccess);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/{taskId}/update-comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Integer taskId, @PathVariable Integer commentId, @RequestBody CommentRequest commentRequest) throws Exception {
        try {
            commentRequest.setTaskId(taskId);
            commentService.updateComment(commentId, commentRequest);
            ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Comment updated successfully");
            return ResponseEntityBuilder.build(apiSuccess);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
