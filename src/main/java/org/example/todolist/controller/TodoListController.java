package org.example.todolist.controller;

import org.example.todolist.dto.request.AssigneeRequest;
import org.example.todolist.dto.request.CommentRequest;
import org.example.todolist.dto.request.TodoListRequest;
import org.example.todolist.dto.response.TodoListResponse;
import org.example.todolist.model.TodoList;
import org.example.todolist.service.AssigneeService;
import org.example.todolist.service.CommentService;
import org.example.todolist.service.TaskService;
import org.example.todolist.service.TodoListService;
import org.example.todolist.web.ApiSuccess;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/todo-list")
public class TodoListController {
    @Autowired
    private TodoListService todolistService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private AssigneeService assigneeService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/")
    public ResponseEntity<List<?>> getAllTodoList() throws Exception {
        return ResponseEntity.ok(todolistService.getAllTodoList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllTodoListByUser(@PathVariable Integer userId ) throws Exception {
        List<TodoListResponse> ownTodoLists = todolistService.getAllTodoList(userId);
        List<TodoListResponse> assignTodoLists = todolistService.getAllTodoListAssignee(userId);
        ownTodoLists.addAll(assignTodoLists);
        return ResponseEntity.ok(ownTodoLists);
    }


    @GetMapping("/user/{userId}/{stateId}")
    public ResponseEntity<List<?>> getAllTodoListByUserIdAndStateId(@PathVariable Integer userId,
                                                                    @PathVariable Integer stateId ) throws Exception{
        return ResponseEntity.ok(todolistService.getAllTodoList(userId, stateId));
    }

    @GetMapping("/{todoListId}")
    public ResponseEntity<?> getTodoList(@PathVariable Integer todoListId) {
        return ResponseEntity.ok(todolistService.getTodoListResponse(todoListId));
    }


    @PutMapping("/create")
    public ResponseEntity<?> createTodoList(@RequestBody TodoListRequest todolist) throws Exception {
        return ResponseEntity.ok(todolistService.create(todolist));
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

    @GetMapping("/{todoListId}/assignees")
    public ResponseEntity<List<?>> getAssignees(@PathVariable Integer todoListId) throws Exception {
        return ResponseEntity.ok(assigneeService.getAssigneeList(todoListId));
    }

    @PutMapping("/{todoListId}/add-assignee")
    public ResponseEntity<?> addAssignee(@PathVariable Integer todoListId, @RequestBody AssigneeRequest assigneeRequest) throws Exception {
        assigneeRequest.setTodoListId(todoListId);
        assigneeService.addAssignee(assigneeRequest);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Assignee added successfully");
        return ResponseEntityBuilder.build(apiSuccess);

    }

    @PostMapping("/{todoListId}/remove-assignee")
    public ResponseEntity<?> delete(@PathVariable Integer todoListId, @RequestBody String email) throws Exception {
        assigneeService.deleteAssignee(todoListId,email);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "Assignee deleted successfully");
        return ResponseEntityBuilder.build(apiSuccess);
    }

    @GetMapping("/{todoListId}/get-comments")
    public ResponseEntity<List<?>> getComments(@PathVariable Integer todoListId) throws Exception {
        return ResponseEntity.ok(commentService.getComments(todoListId));
    }

    @PutMapping("/{todoListId}/add-comment")
    public ResponseEntity<?> addComment(@PathVariable Integer todoListId, @RequestBody CommentRequest commentRequest) throws Exception {
        commentRequest.setTodoListId(todoListId);
        return ResponseEntity.ok(commentService.addComment(commentRequest));

    }

    @PostMapping("/{todoListId}/delete-comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer todoListId, @PathVariable Integer commentId) throws Exception {
        return ResponseEntity.ok(commentService.hide(commentId));

    }

    @PostMapping("/{todoListId}/update-comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Integer todoListId, @PathVariable Integer commentId, @RequestBody CommentRequest commentRequest) throws Exception {
        commentRequest.setTodoListId(todoListId);
        return ResponseEntity.ok(commentService.updateComment(commentId, commentRequest));

    }


}
