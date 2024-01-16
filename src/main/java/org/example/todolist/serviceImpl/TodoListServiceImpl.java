package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.TodoListRequest;
import org.example.todolist.dto.response.TodoListResponse;
import org.example.todolist.model.TodoList;
import org.example.todolist.repository.PriorityRepository;
import org.example.todolist.repository.StateRepository;
import org.example.todolist.repository.TodolistRepository;
import org.example.todolist.service.TaskService;
import org.example.todolist.service.TodoListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TodoListServiceImpl implements TodoListService {
    @Autowired
    private TodolistRepository todolistRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private PriorityRepository priorityRepository;
    @Autowired
    private TaskService taskService;
    @Override
    public TodoList create(TodoList todolist) {
        return todolistRepository.save(todolist);
    }

    @Override
    public TodoListResponse update(Integer id, TodoListRequest todoListRequest) throws Exception {
        TodoList todolist = todolistRepository.findById(id).orElse(null);
        if(todolist != null) {
            todolist.setTitle(todoListRequest.getTitle());
            todolist.setDescription(todoListRequest.getDescription());
            todolist.setOrder(todoListRequest.getOrder());
            todolist.setEstimation(todoListRequest.getEstimation());
            todolist.setStateId(todoListRequest.getStateId());
            todolist.setPriorityId(todoListRequest.getPriorityId());
            todolist.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            todolistRepository.save(todolist);
            TodoListResponse todolistResponse = mapper.map(todolist, TodoListResponse.class);
            todolistResponse.setTasks(taskService.getAllByTodolist(todolist));
            todolistResponse.setUserId(todolist.getUser().getId());
            return todolistResponse;
        }
        throw new Exception("Todo list not found");
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
    public TodoListResponse getTodoListResponse(Integer id) {
        TodoList todolist = todolistRepository.findById(id).orElse(null);
        TodoListResponse todolistResponse = mapper.map(todolist, TodoListResponse.class);
        todolistResponse.setTasks(taskService.getAllByTodolist(todolist));
        todolistResponse.setUserId(todolist.getUser().getId());
        return todolistResponse;
    }

    @Override
    public TodoList getTodolist(Integer id) {
        return todolistRepository.findById(id).orElse(null);
    }

    @Override
    public List<TodoList> getAllTodoList() {
        return todolistRepository.findAll();
    }

    @Override
    public List<TodoList> getAllTodoList(Integer userId) {
        return todolistRepository.findByUserId(userId);
    }

    @Override
    public List<TodoList> getAllTodoList(Integer userId, Integer stateId) {
        return todolistRepository.findByUserIdAndStateId(userId, stateId);
    }
}
