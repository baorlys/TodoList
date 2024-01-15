package org.example.todolist.repository;

import org.example.todolist.model.Task;
import org.example.todolist.model.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.todolist.id = ?1")
    List<Task> getTaskByTodolist(Todolist todolist);
}
