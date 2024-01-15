package org.example.todolist.controller;

import org.example.todolist.model.Todolist;
import org.example.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todolist")
public class    TodolistController {
    @Autowired
    private TodoListService todolistService;

    @GetMapping("/")
    public ResponseEntity<List<Todolist>> getAllTodoList() {
        return ResponseEntity.ok(todolistService.getAllTodoList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Todolist>> getAllTodoListByUserId(@PathVariable Integer userId ) {
        return ResponseEntity.ok(todolistService.getAllTodoList(userId));
    }

    @GetMapping("/user/{userId}/{stateId}")
    public ResponseEntity<List<Todolist>> getAllTodoListByUserIdAndStateId(@PathVariable Integer userId, @PathVariable Integer stateId ) {
        return ResponseEntity.ok(todolistService.getAllTodoList(userId, stateId));
    }


    @PostMapping("/create")
    public ResponseEntity<Todolist> createTodoList(@RequestBody Todolist todolist) {
        return ResponseEntity.ok(todolistService.create(todolist));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTodoListById(@PathVariable Integer id) {
        return ResponseEntity.ok(todolistService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Todolist> updateTodoListById(@PathVariable Integer id, @RequestBody Todolist todolist) {
        return ResponseEntity.ok(todolistService.update(id, todolist));
    }

}
