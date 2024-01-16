package org.example.todolist.repository;

import org.example.todolist.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
