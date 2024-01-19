package org.example.todolist.repository;

import org.example.todolist.model.State;
import org.example.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface StateRepository extends JpaRepository<State, Integer> {
    @Query("SELECT s FROM State s WHERE s.user = ?1")
    List<State> findAllByUser(User user);

    @Query("SELECT s FROM State s WHERE s.user = ?1 AND s.type = ?2")
    List<State> findAllByUserAndType(User user, Integer type);
}
