package org.example.todolist.repository;

import org.example.todolist.model.Label;
import org.example.todolist.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
    List<Label> findLabelsByTodoListsId(Integer todoListId);
}
