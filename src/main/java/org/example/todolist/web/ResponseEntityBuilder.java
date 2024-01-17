package org.example.todolist.web;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    public static ResponseEntity<?> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    public static ResponseEntity<?> build(ApiSuccess apiSuccess) {
        return new ResponseEntity<>(apiSuccess, apiSuccess.getStatus());
    }
}
