package org.example.todolist.web;

import java.time.LocalDateTime;

import lombok.Data;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private HttpStatus status;
    private String message;
    private String path;

    public ApiError(HttpStatus status, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }

}