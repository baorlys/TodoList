package org.example.todolist.serviceImpl;

import org.example.todolist.model.Todolist;
import org.example.todolist.repositories.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodolistServiceImpl {
    @Autowired
    private TodolistRepository todolistRepository;

    public Todolist createNewTodolist(Todolist todolist) {
        return todolistRepository.save(todolist);
    }

    public List<Todolist> getAllTodolist() {
        return todolistRepository.findAll();
    }

    public Optional<Todolist> findTodolistById(Integer id) {
        return todolistRepository.findById(id);
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
        Optional<Todolist> todolistOptional = todolistRepository.findById(id);
        if (todolistOptional.isPresent()) {
            Todolist todolistToUpdate = todolistOptional.get();
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
