package org.example.todolist.controller;

import org.example.todolist.dto.request.ProjectRequest;
import org.example.todolist.dto.response.ProjectResponse;
import org.example.todolist.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ProjectResponse>> getAllProject(@PathVariable Integer userId) throws Exception {
        return ResponseEntity.ok(projectService.getAll(userId));
    }

    @PutMapping("/create")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request) throws Exception {
        return ResponseEntity.ok(projectService.create(request));
    }

    @PostMapping("/update/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Integer projectId, @RequestBody ProjectRequest request) throws Exception {
        return ResponseEntity.ok(projectService.update(projectId, request));
    }

    @PostMapping("/delete/{projectId}")
    public ResponseEntity<Integer> deleteProject(@PathVariable Integer projectId) throws Exception {
        return ResponseEntity.ok(projectService.hide(projectId));
    }

}
