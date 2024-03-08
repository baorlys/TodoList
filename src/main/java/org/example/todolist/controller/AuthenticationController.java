package org.example.todolist.controller;

import jakarta.validation.Valid;
import org.example.todolist.dto.request.AuthenticationRequest;
import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.AuthenticationResponse;
import org.example.todolist.dto.response.SignupResponse;
import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.service.AuthService;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        String jwt = authService.login(authenticationRequest);
        UserResponse userResponse = userService.findByEmail(authenticationRequest.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt, userResponse));
    }
    @PutMapping("/sign-up")
    public ResponseEntity<SignupResponse> signUp(@Valid @RequestBody SignupRequest signupRequest)
            throws Exception {
       SignupResponse createdUser = authService.createUser(signupRequest);
       return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/oauth2")
    @ResponseBody
    public Authentication oauth2(Authentication auth) {

        return auth;
    }






}
