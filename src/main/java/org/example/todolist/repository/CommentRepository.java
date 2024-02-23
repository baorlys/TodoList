package org.example.todolist.repository;

import org.example.todolist.dto.response.CommentResponse;
import org.example.todolist.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.todoList.id = ?1")
    List<Comment> findAllByTodoListId(Integer todoListId);
}
