package org.example.todolist.service;

import org.example.todolist.dto.request.ChangePasswordRequest;
import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.SignupResponse;

public interface AuthService {
    SignupResponse createUser(SignupRequest signupRequest) throws Exception;

    Boolean changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;
}
