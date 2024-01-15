package org.example.todolist.controller;

import org.example.todolist.model.Todolist;
import org.example.todolist.serviceImpl.TodolistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todolist")
public class TodolistController {
    @Autowired
    private TodolistServiceImpl todolistService;

    @GetMapping("/")
    public ResponseEntity<List<Todolist>> getAllTodoList() {
        return ResponseEntity.ok(todolistService.getAllTodolist());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Todolist>> getAllTodoListByUserId(@PathVariable Integer userId ) {
        return ResponseEntity.ok(todolistService.getAllTodolistByUserId(userId));
    }

    @GetMapping("/{userId}/{stateId}")
    public ResponseEntity<List<Todolist>> getAllTodoListByUserIdAndStateId(@PathVariable Integer userId, @PathVariable Integer stateId ) {
        return ResponseEntity.ok(todolistService.getAllTodolistByUserIdAndStateId(userId, stateId));
    }


    @PostMapping("/create")
    public ResponseEntity<Todolist> createNewTodoList(@RequestBody Todolist todolist) {
        return ResponseEntity.ok(todolistService.createNewTodolist(todolist));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTodoListById(@PathVariable Integer id) {
        return ResponseEntity.ok(todolistService.deleteTodolistById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Todolist> updateTodoListById(@PathVariable Integer id, @RequestBody Todolist todolist) {
        return ResponseEntity.ok(todolistService.updateTodolistById(id, todolist));
    }

}
