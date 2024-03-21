package org.example.todolist.controller;

import org.example.todolist.dto.request.LabelRequest;
import org.example.todolist.dto.response.LabelResponse;
import org.example.todolist.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/labels")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping("/todo/{todoId}")
    public ResponseEntity<List<LabelResponse>> getLabelsByTodoId(@PathVariable Integer todoId) {
        return ResponseEntity.ok(labelService.getAllLabelsByTodoId(todoId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LabelResponse>> getLabelsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(labelService.getAllLabelsByUserId(userId));
    }

    @PutMapping("/create")
    public ResponseEntity<LabelResponse> createLabel(@RequestBody LabelRequest label) {
        return ResponseEntity.ok(labelService.createLabel(label));
    }

    @PatchMapping("todo/{todoId}/add")
    public ResponseEntity<LabelResponse> addLabelToTodo(@PathVariable Integer todoId, @RequestBody Integer labelId) {
        return ResponseEntity.ok(labelService.addLabelToTodo(labelId));
    }

    @PatchMapping("todo/{todoId}/tags/{id}/remove")
    public ResponseEntity<Boolean> removeLabelFromTodo(@PathVariable Integer todoId, @PathVariable Integer id) {
        return ResponseEntity.ok(labelService.removeLabelFromTodoById(todoId, id));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteLabel(@PathVariable Integer id) {
        return ResponseEntity.ok(labelService.deleteLabel(id));
    }
}
