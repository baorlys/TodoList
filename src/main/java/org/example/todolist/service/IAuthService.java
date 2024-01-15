package org.example.todolist.service;

import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.UserDTO;

public interface IAuthService {
    UserDTO createUser(SignupRequest signupRequest);
}
