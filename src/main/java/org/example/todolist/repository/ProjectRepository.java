package org.example.todolist.repository;

import org.example.todolist.model.Project;
import org.example.todolist.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT p FROM Project p WHERE p.user.id = ?1 and p.isHidden = false ")
    List<Project> findByUserId(Integer userId);
}
