package org.example.todolist.service;

import org.example.todolist.dto.request.ChangePasswordRequest;
import org.example.todolist.dto.request.UserRequest;
import org.example.todolist.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    List<UserResponse> findAllByIsHidden(Boolean isHidden);
    UserResponse findByEmail(String email) throws Exception;
    UserResponse findById(Integer id) throws Exception;

    void updateLastLogin(String email);
    Boolean changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;
    UserResponse update(Integer id, UserRequest userRequest);
    Boolean delete(Integer id) throws Exception;
    Boolean hide(Integer id) throws Exception;
}
