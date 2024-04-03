package org.example.todolist.service;

import org.example.todolist.dto.request.LabelRequest;
import org.example.todolist.dto.response.LabelResponse;
import org.example.todolist.model.Label;

import java.util.List;

public interface LabelService {
    LabelResponse createLabel(LabelRequest label);
    LabelResponse addLabelToTodo(Integer todoId, Integer labelId);


    LabelResponse getLabelById(Integer id);

    List<LabelResponse> getAllLabelsByUserId(Integer userId);

    List<LabelResponse> getAllLabelsByTodoId(Integer todoId);

    Boolean removeLabelFromTodoById(Integer todoId, Integer id);


    LabelResponse updateLabel(Integer id, Label label);

    Boolean deleteLabel(Integer id);
}
