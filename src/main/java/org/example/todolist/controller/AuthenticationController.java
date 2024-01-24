package org.example.todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.todolist.dto.request.AuthenticationRequest;
import org.example.todolist.dto.request.ChangePasswordRequest;
import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.AuthenticationResponse;
import org.example.todolist.dto.response.SignupResponse;
import org.example.todolist.service.AuthService;
import org.example.todolist.service.UserService;
import org.example.todolist.serviceImpl.jwt.UserDetailsServiceImpl;
import org.example.todolist.util.JwtUtil;
import org.example.todolist.web.ApiError;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @GetMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        String jwt = authService.login(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PutMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest signupRequest)
            throws Exception {
       SignupResponse createdUser = authService.createUser(signupRequest);
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }




}
