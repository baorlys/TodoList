package org.example.todolist.repositories;

import org.example.todolist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findAll();
    Optional<Role> findById(Integer id);
    Role save(Role role);
    void deleteById(Integer id);
}
