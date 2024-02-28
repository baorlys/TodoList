package org.example.todolist.controller;

import org.example.todolist.dto.request.ChangePasswordRequest;
import org.example.todolist.dto.request.UserRequest;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Integer userId) throws Exception {
         return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) throws Exception {
         return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PostMapping("/{userId}/update")
    public ResponseEntity<?> updateUserById(@PathVariable Integer userId, @RequestBody UserRequest userRequest) throws Exception {
        return ResponseEntity.ok(userService.update(userId, userRequest));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        return ResponseEntity.ok(userService.changePassword(changePasswordRequest));
    }

    @PostMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId) throws Exception {
        return ResponseEntity.ok(userService.hide(userId));
    }

}
