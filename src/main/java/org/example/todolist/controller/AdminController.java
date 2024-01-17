package org.example.todolist.controller;

import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/v1/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public ResponseEntity<List<?>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }
}
