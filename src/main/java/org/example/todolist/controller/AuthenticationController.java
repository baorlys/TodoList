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
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @ExceptionHandler({BadCredentialsException.class})
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest,
                                                    HttpServletRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Email or password is incorrect",
                    request.getRequestURI());
            return ResponseEntityBuilder.build(apiError);
        }
        final String jwt = jwtUtil.generateToken(authenticationRequest.getEmail());
        userService.updateLastLogin(authenticationRequest.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest ChangePasswordRequest) throws Exception {
        try {
            Boolean changePassword = authService.changePassword(ChangePasswordRequest);
            return new ResponseEntity<>(changePassword, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) throws Exception {
        try {
            SignupResponse createdUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
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
