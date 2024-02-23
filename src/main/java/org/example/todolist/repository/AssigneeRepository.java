package org.example.todolist.repository;

import org.example.todolist.model.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, Integer> {
    @Query("SELECT a FROM Assignee a WHERE a.todoList.id = ?1 AND a.user.email = ?2")
    Assignee find(Integer todoListId, String email);
}
