package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.TaskRequest;
import org.example.todolist.dto.response.TaskResponse;
import org.example.todolist.model.Priority;
import org.example.todolist.model.Task;
import org.example.todolist.model.TodoList;
import org.example.todolist.model.State;
import org.example.todolist.repository.PriorityRepository;
import org.example.todolist.repository.StateRepository;
import org.example.todolist.repository.TaskRepository;
import org.example.todolist.repository.TodolistRepository;
import org.example.todolist.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TodolistRepository todolistRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public List<TaskResponse> getAllByTodolist(TodoList todolist) {
        List<Task> tasks = taskRepository.getAllByTodolist(todolist);
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task : tasks){
            TaskResponse taskResponse = mapper.map(task, TaskResponse.class);
            if(task.getState() != null) {
                taskResponse.setState(task.getState().getTitle());
            }
            if(task.getPriority() != null) {
                taskResponse.setPriority(task.getPriority().getTitle());
            }
            taskResponses.add(taskResponse);
        }
        return taskResponses;
    }

    @Override
    public List<TaskResponse> getAllByTodolistAndState(TodoList todolist, Integer stateId) {
        List<Task> tasks = taskRepository.getAllByTodolistAndState(todolist,stateId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task : tasks){
            TaskResponse taskResponse = mapper.map(task, TaskResponse.class);
            taskResponses.add(taskResponse);
        }
        return taskResponses;
    }

    @Override
    public TaskResponse find(Integer id) throws Exception {
        Task task = taskRepository.findById(id).orElse(null);
        if(task != null) {
            return mapper.map(task, TaskResponse.class);
        }
        throw new Exception("Task not found");
    }

    @Override
    public TaskResponse createWith(Integer todolistId) {
        Task task = new Task();
        TodoList todolist = todolistRepository.findById(todolistId).orElse(null);
        task.setTodolist(todolist);
        task.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        taskRepository.save(task);
        return mapper.map(task, TaskResponse.class);
    }

    @Override
    public TaskResponse update(Integer id, TaskRequest taskRequest) throws Exception {
        Optional<Task> taskToUpdate = taskRepository.findById(id);
        if(taskToUpdate.isPresent()) {
            Task taskUpdated = taskToUpdate.get();
            Priority priority = priorityRepository.findById(taskRequest.getPriorityId()).orElse(null);
            State state = stateRepository.findById(taskRequest.getStateId()).orElse(null);
            TodoList todolist = todolistRepository.findById(taskRequest.getTodolistId()).orElse(null);
            if(taskRequest.getEstimation().before(taskRequest.getEstimation())){
                throw new Exception("Estimation of task must be before deadline of Todo List");
            }
            taskUpdated.setTitle(taskRequest.getTitle());
            taskUpdated.setDescription(taskRequest.getDescription());
            taskUpdated.setPriority(priority);
            taskUpdated.setState(state);
            taskUpdated.setTodolist(todolist);
            taskUpdated.setOrder(taskRequest.getOrder());
            taskUpdated.setEstimation(taskRequest.getEstimation());
            taskUpdated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            taskRepository.save(taskUpdated);
            return mapper.map(taskUpdated, TaskResponse.class);
        }
        throw new Exception("Task not found");
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
