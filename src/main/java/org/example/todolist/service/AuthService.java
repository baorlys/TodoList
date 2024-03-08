package org.example.todolist.service;

import org.example.todolist.dto.request.AuthenticationRequest;
import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.SignupResponse;

public interface AuthService {
    SignupResponse createUser(SignupRequest signupRequest) throws Exception;

    SignupResponse createUserWithOAuth2(String email, String username) throws Exception;


    String login(AuthenticationRequest authenticationRequest) throws Exception;
}
