package org.example.todolist.service;

import org.example.todolist.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    User findByEmail(String email);
    Optional<User> findById(Integer id);
    User register(User user);
    User login(User user);
    Boolean delete(Integer id);
}
