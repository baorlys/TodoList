package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.UserDTO;
import org.example.todolist.model.User;
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
    private ModelMapper mapper;

    @Override
    public UserDTO createUser(SignupRequest signupRequest) throws Exception {
        if(userRepository.findByEmail(signupRequest.getEmail()) != null) {
            throw new Exception("Email already exists");
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUsername(signupRequest.getUsername());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        User createdUser = userRepository.save(user);
        return mapper.map(createdUser, UserDTO.class);
    }
}
