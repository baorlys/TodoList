package org.example.todolist.service_implement;

import org.example.todolist.dto.request.ProjectRequest;
import org.example.todolist.dto.response.ProjectResponse;
import org.example.todolist.dto.response.TodoListResponse;
import org.example.todolist.model.Project;
import org.example.todolist.model.TodoList;
import org.example.todolist.model.User;
import org.example.todolist.repository.ProjectRepository;
import org.example.todolist.repository.TodolistRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TodolistRepository todolistRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ProjectResponse create(ProjectRequest request) throws Exception {
        Project project = new Project();
        project.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        project.setIsHidden(false);
        return getRequestData(request, project);
    }

    @Override
    public ProjectResponse update(Integer projectId, ProjectRequest request) throws Exception {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            throw new Exception("Project not found");
        }
        return getRequestData(request, project);
    }

    private ProjectResponse getRequestData(ProjectRequest request, Project project) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        project.setTitle(request.getTitle());
        project.setUser(user);
        project.setFromDate(request.getFromDate());
        project.setToDate(request.getToDate());
        return mapper.map(projectRepository.save(project), ProjectResponse.class);
    }

    @Override
    public void delete(Integer projectId) throws Exception {

    }

    @Override
    public Integer hide(Integer projectId) throws Exception {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            throw new Exception("Project not found");
        }
        project.setIsHidden(true);
        projectRepository.save(project);
        return projectId;
    }

    @Override
    public List<ProjectResponse> getAll(Integer userId) throws Exception {
        List<Project> projects = projectRepository.findByUserId(userId);
        List<ProjectResponse> projectResponses = new ArrayList<>();
        for(Project project : projects) {
            projectResponses.add(mapper.map(project, ProjectResponse.class));
        }

        if(projectResponses.isEmpty()) {
            return Collections.emptyList();
        }
        return projectResponses;
    }

    @Override
    public ProjectResponse get(Integer projectId) throws Exception {
        return null;
    }

    @Override
    public List<TodoListResponse> getTodoListsByProjectId(Integer projectId) throws Exception {
        List<TodoList> todoLists = todolistRepository.findByProjectId(projectId);
        List<TodoListResponse> todoListsResponses = new ArrayList<>();
        for(TodoList todoList : todoLists) {
            todoListsResponses.add(mapper.map(todoList, TodoListResponse.class));
        }
        if(todoListsResponses.isEmpty()) {
            return Collections.emptyList();
        }
        return todoListsResponses;
    }
}
