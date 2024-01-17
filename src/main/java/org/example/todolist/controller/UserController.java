package org.example.todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.todolist.dto.request.UserRequest;
import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.model.User;
import org.example.todolist.service.UserService;
import org.example.todolist.serviceImpl.UserServiceImpl;
import org.example.todolist.web.ApiError;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Integer userId) throws Exception {
         return ResponseEntity.ok(userService.findById(userId));
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable Integer userId, @RequestBody UserRequest userRequest) throws Exception {
        return ResponseEntity.ok(userService.update(userId, userRequest));
    }

}
