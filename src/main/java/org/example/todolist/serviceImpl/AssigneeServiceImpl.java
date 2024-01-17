package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.AssigneeRequest;
import org.example.todolist.dto.response.AssigneeResponse;
import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.enums.PermissionAccess;
import org.example.todolist.model.Assignee;
import org.example.todolist.model.Permission;
import org.example.todolist.model.Task;
import org.example.todolist.model.User;
import org.example.todolist.repository.AssigneeRepository;
import org.example.todolist.repository.PermissionRepository;
import org.example.todolist.repository.TaskRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.AssigneeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AssigneeServiceImpl implements AssigneeService {
    @Autowired
    private AssigneeRepository assigneeRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public AssigneeResponse addAssignee(AssigneeRequest assigneeRequest) throws Exception {
        Task task = taskRepository.findById(assigneeRequest.getTaskId()).orElse(null);
        User user = userRepository.findByEmail(assigneeRequest.getEmail());
        Permission permission = permissionRepository.findById(assigneeRequest.getPermissionId()).orElse(null);
        UserResponse userResponse = mapper.map(user, UserResponse.class);
        Assignee assignee = new Assignee();
        assignee.setTask(task);
        assignee.setUser(user);
        assignee.setPermission(permission);
        assignee.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            assigneeRepository.save(assignee);
            AssigneeResponse assigneeResponse = new AssigneeResponse();
            assigneeResponse.setUser(userResponse);
            assigneeResponse.setPermission(PermissionAccess.VIEW);
            return assigneeResponse;
        } catch (Exception e) {
            throw new Exception("Assignee already exists");
        }
    }

    @Override
    public AssigneeResponse setPermission(AssigneeRequest assigneeRequest) throws Exception {
        try {
            Assignee assignee = assigneeRepository.find(assigneeRequest.getTaskId(),assigneeRequest.getEmail());
            Permission permission = permissionRepository.findById(assigneeRequest.getPermissionId()).orElse(null);
            assignee.setPermission(permission);
            assigneeRepository.save(assignee);
            AssigneeResponse assigneeResponse = new AssigneeResponse();
            assigneeResponse.setUser(mapper.map(assignee.getUser(), UserResponse.class));
            assigneeResponse.setPermission(PermissionAccess.getById(permission.getId()));
            return assigneeResponse;
        } catch (Exception e) {
            throw new Exception("Assignee not found");
        }
    }

    @Override
    public void deleteAssignee(Integer taskId, String email) throws Exception {
        try {
            Assignee assignee = assigneeRepository.find(taskId,email);
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
}
