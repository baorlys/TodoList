package org.example.todolist.repository;

import org.example.todolist.model.Task;
import org.example.todolist.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.todolist = ?1")
    List<Task> getAllByTodolist(TodoList todolist);

    @Query("SELECT t FROM Task t WHERE t.todolist = ?1 AND t.state.id = ?2")
    List<Task> getAllByTodolistAndState(TodoList todolist, Integer stateId);

}
