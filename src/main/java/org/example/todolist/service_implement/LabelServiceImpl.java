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

import java.util.ArrayList;
import java.util.Collections;
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
        return mapper.map(labelRepository.save(newLabel), LabelResponse.class);

    }

    @Override
    public LabelResponse addLabelToTodo(Integer todoId,Integer labelId) {
        TodoList todoList = todolistRepository.findById(todoId).orElse(null);
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
        List<Label> labels = labelRepository.findLabelsByUserId(userId);
        if(labels.isEmpty()){
            return Collections.emptyList();
        }
        List<LabelResponse> response = new ArrayList<>();
        for(Label label : labels){
            LabelResponse labelResponse = mapper.map(label, LabelResponse.class);
            response.add(labelResponse);
        }
        return response;
    }

    @Override
    public List<LabelResponse> getAllLabelsByTodoId(Integer todoId) {
        List<Label> labels = labelRepository.findLabelsByTodoListsId(todoId);
        List<LabelResponse> response = new ArrayList<>();
        if(labels.isEmpty()){
            return Collections.emptyList();
        }
        for(Label label : labels){
            LabelResponse labelResponse = mapper.map(label, LabelResponse.class);
            response.add(labelResponse);
        }
        return response;

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
