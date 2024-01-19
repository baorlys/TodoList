package org.example.todolist.service;

import org.example.todolist.dto.request.StateRequest;
import org.example.todolist.dto.response.StateResponse;

import java.util.List;

public interface StateService {
    void createDefautStates(Integer userId) throws Exception;
    void createState(Integer userId, StateRequest stateRequest) throws Exception;

    void updateState(Integer userId, Integer stateId, StateRequest stateRequest) throws Exception;
    List<StateResponse> getStatesByUserId(Integer userId) throws Exception;

    List<StateResponse> getStatesByUserIdAndType(Integer userId, Integer type) throws Exception;

}
