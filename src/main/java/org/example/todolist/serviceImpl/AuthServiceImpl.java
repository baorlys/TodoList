package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.UserDTO;
import org.example.todolist.model.User;
import org.example.todolist.repositories.UserRepository;
import org.example.todolist.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUsername(signupRequest.getUsername());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setUsername(createdUser.getUsername());
        return userDTO;
    }
}
