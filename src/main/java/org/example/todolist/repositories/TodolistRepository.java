package org.example.todolist.repositories;

import org.example.todolist.model.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist, Integer> {
    public List<Todolist> findAll();
    Optional<Todolist> findById(Integer id);

    List<Todolist> findByUserId(Integer userId);

    List<Todolist> findByStateId(Integer stateId);

    List<Todolist> findByUserIdAndStateId(Integer userId, Integer stateId);
}
