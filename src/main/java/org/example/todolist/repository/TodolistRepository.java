package org.example.todolist.repository;

import org.example.todolist.model.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist, Integer> {

    @Query("SELECT t FROM Todolist t WHERE t.user.id = ?1")
    List<Todolist> findByUserId(Integer userId);

    @Query("SELECT t FROM Todolist t WHERE t.user.id = ?1 AND t.stateId = ?2")
    List<Todolist> findByUserIdAndStateId(Integer userId, Integer stateId);
}
