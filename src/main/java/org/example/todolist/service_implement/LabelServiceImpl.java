package org.example.todolist.service_implement;

import org.example.todolist.dto.request.LabelRequest;
import org.example.todolist.dto.response.LabelResponse;
import org.example.todolist.model.Label;
import org.example.todolist.model.TodoList;
import org.example.todolist.repository.LabelRepository;
import org.example.todolist.repository.TodolistRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.LabelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodolistRepository todolistRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public LabelResponse createLabel(LabelRequest label){
        Label newLabel = new Label();
        newLabel.setTitle(label.getTitle());
        newLabel.setColor(label.getColor());
        newLabel.setUser(userRepository.findById(label.getUserId()).orElse(null));
        TodoList todoList = todolistRepository.findById(label.getTodoId()).orElse(null);
        if(todoList != null){
            todoList.addLabel(newLabel);
            todolistRepository.save(todoList);
        }
        return mapper.map(labelRepository.save(newLabel), LabelResponse.class);

    }

    @Override
    public LabelResponse addLabelToTodo(Integer labelId) {
        TodoList todoList = todolistRepository.findById(labelId).orElse(null);
        Label label = labelRepository.findById(labelId).orElse(null);
        if(todoList != null && label != null){
            todoList.addLabel(label);
            todolistRepository.save(todoList);
        }
        return mapper.map(label, LabelResponse.class);
    }

    @Override
    public LabelResponse getLabelById(Integer id) {
        return null;
    }

    @Override
    public List<LabelResponse> getAllLabelsByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<LabelResponse> getAllLabelsByTodoId(Integer todoId) {
        return null;
    }

    @Override
    public Boolean removeLabelFromTodoById(Integer todoId, Integer id) {
        TodoList todoList = todolistRepository.findById(todoId).orElse(null);
        Label label = labelRepository.findById(id).orElse(null);
        if(todoList != null && label != null){
            todoList.removeLabel(id);
            todolistRepository.save(todoList);
            return true;
        }
        return false;
    }

    @Override
    public LabelResponse updateLabel(Integer id, Label label) {
        return null;
    }

    @Override
    public Boolean deleteLabel(Integer id) {
        return null;
    }
}
