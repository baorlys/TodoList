package org.example.todolist.repository;

import org.example.todolist.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.todoList.id = ?1 AND c.isHidden = false")
    List<Comment> findAllByTodoListId(Integer todoListId);
}
