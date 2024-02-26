package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.AssigneeRequest;
import org.example.todolist.dto.request.TodoListRequest;
import org.example.todolist.dto.response.TodoListResponse;
import org.example.todolist.model.Priority;
import org.example.todolist.model.State;
import org.example.todolist.model.TodoList;
import org.example.todolist.model.User;
import org.example.todolist.repository.PriorityRepository;
import org.example.todolist.repository.StateRepository;
import org.example.todolist.repository.TodolistRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.AssigneeService;
import org.example.todolist.service.TaskService;
import org.example.todolist.service.TodoListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssigneeService assigneeService;

    @Override
    public TodoListResponse create(TodoListRequest todoListRequest) throws Exception {
        TodoList todolist = new TodoList();
        todolist.setPriority(priorityRepository.findById(4).orElse(null));
        User user = userRepository.findById(todoListRequest.getUserId()).orElse(null);
        todolist.setUser(user);
        return getTodoListResponse(todoListRequest, todolist);
    }

    private TodoListResponse getTodoListResponse(TodoListRequest todoListRequest, TodoList todolist) throws Exception {
        Priority priority = priorityRepository.findById(todoListRequest.getPriorityId()).orElse(null);
        State state = stateRepository.findByUserIdAndType(todoListRequest.getUserId(), todoListRequest.getTypeId());
        todolist.setTitle(todoListRequest.getTitle());
        todolist.setDescription(todoListRequest.getDescription());
        todolist.setOrder(todoListRequest.getOrder());
        todolist.setEstimation(todoListRequest.getEstimation());
        todolist.setState(state);
        todolist.setPriority(priority);
        todolist.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        todolist.setIsHidden(false);
        todolistRepository.save(todolist);
        TodoListResponse todolistResponse = mapper.map(todolist, TodoListResponse.class);
        todolistResponse.setTasks(taskService.getAllByTodolist(todolist));
        try {
            todolistResponse.setAssignees(assigneeService.getAssigneeList(todolist.getId()));
        } catch (Exception e) {
            todolistResponse.setAssignees(Collections.emptyList());
        }
        todolistResponse.setUserId(todolist.getUser().getId());
        return todolistResponse;
    }

    @Override
    public TodoListResponse update(Integer id, TodoListRequest todoListRequest) throws Exception {
        TodoList todolist = todolistRepository.findById(id).orElse(null);
        if(todolist != null) {
            return getTodoListResponse(todoListRequest, todolist);
        }
        throw new Exception("Todo list not found");
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        try {
            todolistRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception("Todo list not found");
        }
    }

    @Override
    public Boolean hide(Integer id) throws Exception {
        TodoList todolist = todolistRepository.findById(id).orElse(null);
        if(todolist != null) {
            todolist.setIsHidden(true);
            todolist.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            todolistRepository.save(todolist);
            return true;
        }
        throw new Exception("Todo list not found");
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
    public TodoListResponse getTodoList(Integer id) throws Exception {
        TodoList todolist = todolistRepository.findById(id).orElse(null);
        if(todolist != null) {
            return mapper.map(todolist, TodoListResponse.class);
        }
        throw new Exception("Todo list not found");
    }

    @Override
    public List<TodoListResponse> getAllTodoList() throws Exception {
        List<TodoList> todoLists = todolistRepository.findAll();
        return getTodoListResponses(todoLists);
    }

    @Override
    public List<TodoListResponse> getAllTodoList(Integer userId) throws Exception{
        List<TodoList> todoLists = todolistRepository.findByUserId(userId);
        return getTodoListResponses(todoLists);
    }

    private List<TodoListResponse> getTodoListResponses(List<TodoList> todoLists) throws Exception {
        List<TodoListResponse> todolistResponses = new ArrayList<>();
        for(TodoList todolist : todoLists) {
            TodoListResponse todolistResponse = mapper.map(todolist, TodoListResponse.class);
            todolistResponse.setTasks(taskService.getAllByTodolist(todolist));
            try {
                todolistResponse.setAssignees(assigneeService.getAssigneeList(todolist.getId()));
            } catch (Exception e) {
                todolistResponse.setAssignees(Collections.emptyList());
            }
            todolistResponse.setUserId(todolist.getUser().getId());
            todolistResponses.add(todolistResponse);
        }
        return todolistResponses;
    }

    @Override
    public List<TodoListResponse> getAllTodoList(Integer userId, Integer stateId) throws Exception {
        List<TodoList> todoLists = todolistRepository.findByUserIdAndStateId(userId, stateId);
        return getTodoListResponses(todoLists);
    }
}
