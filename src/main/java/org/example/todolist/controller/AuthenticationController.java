package org.example.todolist.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.todolist.dto.request.AuthenticationRequest;
import org.example.todolist.dto.response.AuthenticationResponse;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @ExceptionHandler({BadCredentialsException.class})
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest,
                                                    HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Email or password is incorrect", Collections.singletonList(e.getMessage()));
            return ResponseEntityBuilder.build(apiError);
        }
        final String jwt = jwtUtil.generateToken(authenticationRequest.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
