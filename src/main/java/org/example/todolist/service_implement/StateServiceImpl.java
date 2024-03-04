package org.example.todolist.service_implement;

import org.example.todolist.dto.request.StateRequest;
import org.example.todolist.dto.response.StateResponse;
import org.example.todolist.enums.StateType;
import org.example.todolist.model.State;
import org.example.todolist.model.User;
import org.example.todolist.repository.StateRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.StateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public void createDefautStates(Integer userId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new Exception("User not found");
        }
        stateRepository.saveAll(List.of(
                new State(null, "Todo", StateType.TODO.getTypeId(), user),
                new State(null, "Doing",StateType.DOING.getTypeId(), user),
                new State(null, "Done",StateType.DONE.getTypeId(), user)
        ));
    }

    @Override
    public void createState(Integer userId, StateRequest stateRequest) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new Exception("User not found");
        }
        stateRepository.save(new State(null, stateRequest.getTitle(), stateRequest.getType(), user));
    }

    @Override
    public void updateState(Integer userId, Integer stateId, StateRequest stateRequest) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new Exception("User not found");
        }
        State state = stateRepository.findById(stateId).orElse(null);
        if (state == null) {
            throw new Exception("State not found");
        }
        state.setTitle(stateRequest.getTitle());
        state.setType(stateRequest.getType());
        stateRepository.save(state);
    }

    @Override
    public List<StateResponse> getStatesByUserId(Integer userId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new Exception("User not found");
        }
        List<State> states = stateRepository.findAllByUser(user);
        List<StateResponse> stateResponses = new ArrayList<>();
        for(State state : states) {
            StateResponse stateResponse = mapper.map(state, StateResponse.class);
            stateResponses.add(stateResponse);
        }
        return stateResponses;
    }

    @Override
    public List<StateResponse> getStatesByUserIdAndType(Integer userId, Integer type) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new Exception("User not found");
        }
        List<State> states = stateRepository.findAllByUserAndType(user,type);
        List<StateResponse> stateResponses = new ArrayList<>();
        for(State state : states) {
            StateResponse stateResponse = mapper.map(state, StateResponse.class);
            stateResponses.add(stateResponse);
        }
        return stateResponses;
    }
}
