package org.example.todolist.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.example.todolist.web.ApiError;
import org.example.todolist.web.ResponseEntityBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI());
        return ResponseEntityBuilder.build(apiError);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), request.getRequestURI());
        return ResponseEntityBuilder.build(apiError);
    }

}
