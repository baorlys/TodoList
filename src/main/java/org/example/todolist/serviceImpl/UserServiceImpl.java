package org.example.todolist.serviceImpl;

import org.example.todolist.dto.request.ChangePasswordRequest;
import org.example.todolist.dto.request.UserRequest;
import org.example.todolist.dto.response.UserResponse;
import org.example.todolist.model.Role;
import org.example.todolist.model.User;
import org.example.todolist.repository.RoleRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = mapper.map(user, UserResponse.class);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @Override
    public UserResponse findByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return mapper.map(user, UserResponse.class);
        }
        throw new Exception("User not found");
    }

    @Override
    public UserResponse findById(Integer id) throws Exception {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            return mapper.map(user, UserResponse.class);
        }
        throw new Exception("User not found");
    }

    @Override
    public void updateLastLogin(String email) {
        User user = userRepository.findByEmail(email);
        user.setLastloginAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
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

    @Override
    public UserResponse update(Integer id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setMobile(userRequest.getMobile());
            user.setMobileHidden(userRequest.getMobileHidden());
            user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        }
        return null;
    }




    @Override
    public Boolean delete(Integer id) throws Exception {
        try {
            userRepository.deleteById(id);
            return true;
        }  catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
