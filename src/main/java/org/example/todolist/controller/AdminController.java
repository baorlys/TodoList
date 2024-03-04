package org.example.todolist.controller;

import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/v1/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/{isHidden}")
    public ResponseEntity<List<UserResponse>> getAllUserByIsHidden(@PathVariable Boolean isHidden) {
        return ResponseEntity.ok(userService.findAllByIsHidden(isHidden));
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Integer userId) throws Exception {
        return ResponseEntity.ok(userService.delete(userId));
    }
}
