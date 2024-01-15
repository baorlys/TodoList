package org.example.todolist.repositories;

import org.example.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    boolean existsByEmail(String email);
    User findByEmail(String email);
    Optional<User> findById(Integer id);
    User save(User user);
    void deleteById(Integer id);

}

