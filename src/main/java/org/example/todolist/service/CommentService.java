package org.example.todolist.service;

import org.example.todolist.dto.request.CommentRequest;
import org.example.todolist.dto.response.CommentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    CommentResponse addComment(CommentRequest commentRequest) throws Exception;

    void deleteComment(Integer commentId) throws Exception;

    Integer hide(Integer commentId) throws Exception;

    CommentResponse updateComment(Integer commentId, CommentRequest commentRequest) throws Exception;

    List<CommentResponse> getComments(Integer todoListId) throws Exception;
}
