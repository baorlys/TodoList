package org.example.todolist.services;

import org.example.todolist.models.Todolist;
import org.example.todolist.repositories.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodolistService {
    @Autowired
    private TodolistRepository todolistRepository;

    public Todolist createNewTodolist(Todolist todolist) {
        return todolistRepository.save(todolist);
    }

    public List<Todolist> getAllTodolist() {
        return todolistRepository.findAll();
    }

    public Todolist findTodolistById(Integer id) {
        return todolistRepository.findById(id).orElse(null);
    }

    public List<Todolist> findTodoListByState(Integer stateId) {
        return todolistRepository.findByStateId(stateId);
    }


    public boolean deleteTodolistById(Integer id) {
        try {
            todolistRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Todolist updateTodolistById(Integer id, Todolist todolist) {
        Todolist todolistToUpdate = todolistRepository.findById(id).orElse(null);
        if (todolistToUpdate != null) {
            todolistToUpdate.setTitle(todolist.getTitle());
            todolistToUpdate.setDescription(todolist.getDescription());
            todolistToUpdate.setEstimation(todolist.getEstimation());
            todolistToUpdate.setOrder(todolist.getOrder());
            todolistToUpdate.setPriorityId(todolist.getPriorityId());
            todolistToUpdate.setStateId(todolist.getStateId());
            todolistToUpdate.setUpdatedAt(todolist.getUpdatedAt());
            return todolistRepository.save(todolistToUpdate);
        }
        return null;
    }

    public List<Todolist> getAllTodolistByUserId(Integer userId) {
        return todolistRepository.findByUserId(userId);
    }

    public List<Todolist> getAllTodolistByUserIdAndStateId(Integer userId, Integer stateId) {
        return todolistRepository.findByUserIdAndStateId(userId, stateId);
    }
}
