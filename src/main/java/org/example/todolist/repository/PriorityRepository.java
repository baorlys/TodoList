package org.example.todolist.repository;

import org.example.todolist.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}