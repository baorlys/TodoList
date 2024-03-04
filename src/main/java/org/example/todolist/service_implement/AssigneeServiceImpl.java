package org.example.todolist.service_implement;

import org.example.todolist.dto.request.AssigneeRequest;
import org.example.todolist.dto.response.AssigneeResponse;
import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.enums.PermissionAccess;
import org.example.todolist.model.*;
import org.example.todolist.model.composite_key.AssigneeId;
import org.example.todolist.repository.*;
import org.example.todolist.service.AssigneeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssigneeServiceImpl implements AssigneeService {
    @Autowired
    private AssigneeRepository assigneeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TodolistRepository todolistRepository;

    @Override
    public void addAssignee(AssigneeRequest assigneeRequest) throws Exception {
        TodoList todoList = todolistRepository.findById(assigneeRequest.getTodoListId()).orElse(null);
        User user = userRepository.findByEmail(assigneeRequest.getEmail());
        Permission permission = permissionRepository.findById(assigneeRequest.getPermissionId()).orElse(null);
//        UserResponse userResponse = mapper.map(user, UserResponse.class);
        Assignee assignee = new Assignee();
        AssigneeId assigneeId = new AssigneeId(todoList,user);
        assignee.setId(assigneeId);
        assignee.setPermission(permission);
        assignee.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            assigneeRepository.save(assignee);
//            AssigneeResponse assigneeResponse = new AssigneeResponse();
//            assigneeResponse.setUser(userResponse);
//            assigneeResponse.setPermission(PermissionAccess.VIEW);
        } catch (Exception e) {
            throw new Exception("Assignee already exists");
        }
    }

    @Override
    public AssigneeResponse setPermission(AssigneeRequest assigneeRequest) throws Exception {
        try {
            Assignee assignee = assigneeRepository.find(assigneeRequest.getTodoListId(),assigneeRequest.getEmail());
            Permission permission = permissionRepository.findById(assigneeRequest.getPermissionId()).orElse(null);
            assignee.setPermission(permission);
            assigneeRepository.save(assignee);
            AssigneeResponse assigneeResponse = new AssigneeResponse();
            assigneeResponse.setUser(mapper.map(assignee.getId().getUser(), UserResponse.class));
            assigneeResponse.setPermission(PermissionAccess.getById(permission.getId()));
            return assigneeResponse;
        } catch (Exception e) {
            throw new Exception("Assignee not found");
        }
    }

    @Override
    public void deleteAssignee(Integer todoListId, String email) throws Exception {
        try {
            Assignee assignee = assigneeRepository.find(todoListId,email);
            assigneeRepository.delete(assignee);
        } catch (Exception e) {
            throw new Exception("Assignee not found");
        }
    }

    @Override
    public UserResponse getAssigneeInfo(String email) throws Exception {
        try {
            User user = userRepository.findByEmail(email);
            UserResponse userResponse = mapper.map(user, UserResponse.class);
            if(user.getMobileHidden()) {
                userResponse.setMobile(null);
            }
            return userResponse;
        } catch (Exception e) {
            throw new Exception("Assignee not found");
        }
    }

    @Override
    public List<AssigneeResponse> getAssigneeList(Integer todoListId) throws Exception {
        try {
            List<Assignee> assigneeList = assigneeRepository.findAllByTodoList(todoListId);
            List<AssigneeResponse> assigneeResponseList = new ArrayList<>();
            for(Assignee assignee: assigneeList) {
                AssigneeResponse assigneeResponse = new AssigneeResponse();
                if(assignee.getId().getUser().getMobileHidden()) {
                    assigneeResponse.setUser(mapper.map(assignee.getId().getUser(), UserResponse.class));
                    assigneeResponse.getUser().setMobile(null);
                } else {
                    assigneeResponse.setUser(mapper.map(assignee.getId().getUser(), UserResponse.class));
                }
                assigneeResponse.setPermission(PermissionAccess.getById(assignee.getPermission().getId()));
                assigneeResponseList.add(assigneeResponse);
            }
            return assigneeResponseList;
        } catch (Exception e) {
            throw new Exception("Assignee not found");
        }
    }

}
