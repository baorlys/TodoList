package org.example.todolist.service_implement;

import org.example.todolist.model.Priority;
import org.example.todolist.repository.PriorityRepository;
import org.example.todolist.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    @Autowired
    private PriorityRepository priorityRepository;
    @Override
    public void createDefautPriorities() {
        priorityRepository.saveAll(List.of(
                new Priority(1, "Low"),
                new Priority(2, "Medium"),
                new Priority(3, "High")
        ));
    }
}
