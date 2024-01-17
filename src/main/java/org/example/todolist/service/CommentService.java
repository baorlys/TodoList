package org.example.todolist.service;

import org.example.todolist.dto.request.CommentRequest;
import org.example.todolist.dto.response.CommentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    void addComment(CommentRequest commentRequest) throws Exception;

    void deleteComment(Integer commentId) throws Exception;

    void updateComment(Integer commentId, CommentRequest commentRequest) throws Exception;

    public List<CommentResponse> getComments(Integer taskId) throws Exception;
}
