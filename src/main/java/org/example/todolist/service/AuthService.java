package org.example.todolist.service;

import org.example.todolist.dto.request.AuthenticationRequest;
import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.SignupResponse;

public interface AuthService {
    SignupResponse createUser(SignupRequest signupRequest) throws Exception;


    String login(AuthenticationRequest authenticationRequest) throws Exception;
}
