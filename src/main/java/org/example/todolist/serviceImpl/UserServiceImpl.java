package org.example.todolist.serviceImpl;

import org.example.todolist.model.Role;
import org.example.todolist.model.User;
import org.example.todolist.repository.RoleRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public User update(Integer id, User user) {
        return null;
    }


    public boolean isExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }


    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
