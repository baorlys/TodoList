package org.example.todolist.serviceImpl;

import org.example.todolist.enums.StateType;
import org.example.todolist.model.State;
import org.example.todolist.model.User;
import org.example.todolist.repository.StateRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private UserRepository userRepository;
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
}
