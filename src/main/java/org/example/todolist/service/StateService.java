package org.example.todolist.service;

import org.example.todolist.dto.request.StateRequest;

public interface StateService {
    void createDefautStates(Integer userId) throws Exception;
    void createState(Integer userId, StateRequest stateRequest) throws Exception;
}
