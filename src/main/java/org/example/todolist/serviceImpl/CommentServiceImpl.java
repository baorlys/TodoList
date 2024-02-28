package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.CommentRequest;
import org.example.todolist.dto.response.CommentResponse;
import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.model.Comment;
import org.example.todolist.model.Task;
import org.example.todolist.model.TodoList;
import org.example.todolist.model.User;
import org.example.todolist.repository.CommentRepository;
import org.example.todolist.repository.TaskRepository;
import org.example.todolist.repository.TodolistRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TodolistRepository todolistRepository;

    @Override
    public CommentResponse addComment(CommentRequest commentRequest) throws Exception {
        Comment comment = new Comment();
        User user = userRepository.findById(commentRequest.getUserId()).orElse(null);
        comment.setUser(user);
        comment.setIsHidden(false);
        TodoList todoList = todolistRepository.findById(commentRequest.getTodoListId()).orElse(null);
        comment.setTodoList(todoList);
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            commentRepository.save(comment);
            return mapper.map(comment, CommentResponse.class);
        } catch (Exception e) {
            throw new Exception("Cannot add comment");
        }
    }

    @Override
    public void deleteComment(Integer commentId) throws Exception {
        try {
            commentRepository.deleteById(commentId);
        } catch (Exception e) {
            throw new Exception("Cannot delete comment");
        }
    }

    @Override
    public Integer hide(Integer commentId) throws Exception {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null) {
            throw new Exception("Comment not found");
        }
        comment.setIsHidden(true);
        comment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            commentRepository.save(comment);
            return comment.getId();
        } catch (Exception e) {
            throw new Exception("Error");
        }
    }

    @Override
    public CommentResponse updateComment(Integer commentId, CommentRequest commentRequest) throws Exception {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(commentRequest.getContent().isEmpty()) {
            throw new Exception("Content cannot be empty");
        }
        comment.setContent(commentRequest.getContent());
        comment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            commentRepository.save(comment);
            return mapper.map(comment, CommentResponse.class);
        } catch (Exception e) {
            throw new Exception("Cannot update comment");
        }
    }

    @Override
    public List<CommentResponse> getComments(Integer todoListId) throws Exception {
        List<Comment> comments = null;
        try {
            comments = commentRepository.findAllByTodoListId(todoListId);
        } catch (Exception e) {
            throw new Exception("Cannot get comments");
        }
        if(comments.isEmpty()) {
            return Collections.emptyList();
        }
        List<CommentResponse> commentResponses = new ArrayList<>();
        for(Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setContent(comment.getContent());
            commentResponse.setUser(mapper.map(comment.getUser(), UserResponse.class));
            commentResponse.setCreatedAt(comment.getCreatedAt());
            commentResponses.add(commentResponse);
        }
        return commentResponses;
    }
}
