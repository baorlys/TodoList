package org.example.todolist.controller;

import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.UserDTO;
import org.example.todolist.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class SignupController {
    @Autowired
    private IAuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest) {
        UserDTO createdUser = authService.createUser(signupRequest);
        if(createdUser == null) {
            return new ResponseEntity<>("User is not created, try again", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
