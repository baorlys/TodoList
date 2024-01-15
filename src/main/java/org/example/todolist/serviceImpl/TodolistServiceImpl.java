package org.example.todolist.serviceImpl;

import org.example.todolist.model.Todolist;
import org.example.todolist.repository.TodolistRepository;
import org.example.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodolistServiceImpl implements TodoListService {
    @Autowired
    private TodolistRepository todolistRepository;

    @Override
    public Todolist create(Todolist todolist) {
        return todolistRepository.save(todolist);
    }

    @Override
    public Todolist update(Integer id, Todolist todolist) {
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

    @Override
    public Boolean delete(Integer id) {
        try {
            todolistRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Todolist> getTodoList(Integer id) {
        return todolistRepository.findById(id);
    }

    @Override
    public List<Todolist> getAllTodoList() {
        return todolistRepository.findAll();
    }

    @Override
    public List<Todolist> getAllTodoList(Integer userId) {
        return todolistRepository.findByUserId(userId);
    }

    @Override
    public List<Todolist> getAllTodoList(Integer userId, Integer stateId) {
        return todolistRepository.findByUserIdAndStateId(userId, stateId);
    }
}
