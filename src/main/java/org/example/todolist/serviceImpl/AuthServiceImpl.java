package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.ChangePasswordRequest;
import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.SignupResponse;
import org.example.todolist.model.Role;
import org.example.todolist.model.User;
import org.example.todolist.repository.RoleRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public SignupResponse createUser(SignupRequest signupRequest) throws Exception {
        if(userRepository.findByEmail(signupRequest.getEmail()) != null) {
            throw new Exception("Email already exists");
        }
        Role role = roleRepository.findById(2).orElse(null);
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setRole(role);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUsername(signupRequest.getUsername());
        user.setLastloginAt(new Timestamp(System.currentTimeMillis()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        User createdUser = userRepository.save(user);
        return mapper.map(createdUser, SignupResponse.class);
    }

    @Override
    public Boolean changePassword(ChangePasswordRequest changePasswordRequest) throws Exception {
        if (userRepository.findByEmail(changePasswordRequest.getEmail()) == null) {
            throw new Exception("Email not found");
        }
        User user = userRepository.findByEmail(changePasswordRequest.getEmail());
        if (new BCryptPasswordEncoder().matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(changePasswordRequest.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        throw new Exception("Old password is incorrect");
    }
}
