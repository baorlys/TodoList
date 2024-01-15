package org.example.todolist.serviceImpl;

import org.example.todolist.model.Role;
import org.example.todolist.model.User;
import org.example.todolist.repositories.RoleRepository;
import org.example.todolist.repositories.UserRepository;
import org.example.todolist.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
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
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public User register(User user) {
        User newUser = new User();
        List<Role> roles = getRoles();
        // 1 is user , 0 is admin
        newUser.setRole(roles.get(1));
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());
        newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(newUser);

    }

    public User register(User user, String passwordHash) {
        User newUser = new User();
        List<Role> roles = getRoles();
        // 1 is user , 0 is admin
        newUser.setRole(roles.get(1));
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordHash);
        newUser.setUsername(user.getUsername());
        newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(newUser);

    }

    public boolean isExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
