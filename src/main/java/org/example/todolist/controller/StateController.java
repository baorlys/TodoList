package org.example.todolist.controller;

import org.example.todolist.dto.request.StateRequest;
import org.example.todolist.service.StateService;
import org.example.todolist.web.ApiSuccess;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/state")
public class StateController {
    @Autowired
    private StateService stateService;

    @PostMapping("{userId}/create-state")
    public ResponseEntity<?> getAllState(@PathVariable Integer userId, @RequestBody StateRequest stateRequest) throws Exception {
        stateService.createState(userId, stateRequest);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "State created successfully");
        return ResponseEntityBuilder.build(apiSuccess);
    }

    @PostMapping("{userId}/update-state/{stateId}")
    public ResponseEntity<?> updateState(@PathVariable Integer userId, @PathVariable Integer stateId, @RequestBody StateRequest stateRequest) throws Exception {
        stateService.updateState(userId, stateId, stateRequest);
        ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK, "State updated successfully");
        return ResponseEntityBuilder.build(apiSuccess);
    }

    @GetMapping("{userId}/get-states")
    public ResponseEntity<?> getStatesByUserId(@PathVariable Integer userId) throws Exception {
        return ResponseEntity.ok(stateService.getStatesByUserId(userId));
    }

    @GetMapping("{userId}/get-states-by-type/{type}")
    public ResponseEntity<?> getStatesByUserIdAndType(@PathVariable Integer userId, @PathVariable Integer type) throws Exception {
        return ResponseEntity.ok(stateService.getStatesByUserIdAndType(userId, type));
    }
}
