package org.example.todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.todolist.dto.request.UserRequest;
import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.model.User;
import org.example.todolist.serviceImpl.UserServiceImpl;
import org.example.todolist.web.ApiError;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public ResponseEntity<List<?>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Integer userId) throws Exception {
        try {
            return ResponseEntity.ok(userService.findById(userId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable Integer userId, @RequestBody UserRequest userRequest) throws Exception {
        try {
            return ResponseEntity.ok(userService.update(userId, userRequest));
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
