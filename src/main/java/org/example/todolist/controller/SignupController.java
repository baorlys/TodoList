package org.example.todolist.controller;

import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.UserDTO;
import org.example.todolist.service.AuthService;
import org.example.todolist.web.ApiError;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class SignupController {
    @Autowired
    private AuthService authService;


    @PutMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) throws Exception {
        try {
            UserDTO createdUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), List.of());
            return ResponseEntityBuilder.build(error);
        }
    }

}
