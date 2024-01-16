package org.example.todolist.repository;

import org.example.todolist.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodolistRepository extends JpaRepository<TodoList, Integer> {

    @Query("SELECT t FROM TodoList t WHERE t.user.id = ?1")
    List<TodoList> findByUserId(Integer userId);

    @Query("SELECT t FROM TodoList t WHERE t.user.id = ?1 AND t.stateId = ?2")
    List<TodoList> findByUserIdAndStateId(Integer userId, Integer stateId);
}
