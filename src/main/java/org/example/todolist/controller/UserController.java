package org.example.todolist.controller;

import org.example.todolist.model.User;
import org.example.todolist.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        Optional<User> user = userService.findById(userId);
        return user.orElse(null);
    }

}
