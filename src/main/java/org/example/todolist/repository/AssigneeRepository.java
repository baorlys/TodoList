package org.example.todolist.repository;

import org.example.todolist.model.Assignee;
import org.example.todolist.model.TodoList;
import org.example.todolist.model.composite_key.AssigneeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, AssigneeId> {
    @Query("SELECT a FROM Assignee a WHERE a.id.todoList.id = ?1 AND a.id.user.email = ?2")
    Assignee find(Integer todoListId, String email);

    @Query("SELECT a FROM Assignee a WHERE a.id.todoList.id = ?1")
    List<Assignee> findAllByTodoList(Integer todoListId);

    @Query("SELECT a.id.todoList FROM Assignee a WHERE a.id.user.id = ?1")
    List<TodoList> findAllByUserId(Integer userId);

}
