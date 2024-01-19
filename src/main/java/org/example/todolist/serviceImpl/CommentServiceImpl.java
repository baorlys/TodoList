package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.CommentRequest;
import org.example.todolist.dto.response.CommentResponse;
import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.model.Comment;
import org.example.todolist.model.Task;
import org.example.todolist.model.User;
import org.example.todolist.repository.CommentRepository;
import org.example.todolist.repository.TaskRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    @Override
    public void addComment(CommentRequest commentRequest) throws Exception {
        Comment comment = new Comment();
        User user = userRepository.findById(commentRequest.getUserId()).orElse(null);
        comment.setUser(user);
        Task task = taskRepository.findById(commentRequest.getTaskId()).orElse(null);
        comment.setTask(task);
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            commentRepository.save(comment);
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
    public Boolean hide(Integer commentId) throws Exception {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null) {
            throw new Exception("Comment not found");
        }
        comment.setIsHidden(true);
        comment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            throw new Exception("Error");
        }
    }

    @Override
    public void updateComment(Integer commentId, CommentRequest commentRequest) throws Exception {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(commentRequest.getContent().isEmpty()) {
            throw new Exception("Content cannot be empty");
        }
        comment.setContent(commentRequest.getContent());
        comment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            commentRepository.save(comment);
        } catch (Exception e) {
            throw new Exception("Cannot update comment");
        }
    }

    @Override
    public List<CommentResponse> getComments(Integer taskId) throws Exception {
        List<Comment> comments = null;
        try {
            comments = commentRepository.findAllByTaskId(taskId);
        } catch (Exception e) {
            throw new Exception("Cannot get comments");
        }
        if(comments.isEmpty()) {
            throw new Exception("No comments");
        }
        List<CommentResponse> commentResponses = new ArrayList<>();
        for(Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setContent(comment.getContent());
            commentResponse.setUser(mapper.map(comment.getUser(), UserResponse.class));
            commentResponses.add(commentResponse);
        }
        return commentResponses;
    }
}
