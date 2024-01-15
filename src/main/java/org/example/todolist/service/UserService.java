package org.example.todolist.service;

import org.example.todolist.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    User findByEmail(String email);
    Optional<User> findById(Integer id);

    User update(Integer id, User user);
    Boolean delete(Integer id);
}
