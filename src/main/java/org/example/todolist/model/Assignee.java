package org.example.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.todolist.model.compositeKey.AssigneeId;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(AssigneeId.class)
@Table(name = "assignees")
public class Assignee {
    @Id
    @ManyToOne()
    @JoinColumn(name = "task_id", nullable = false, referencedColumnName = "id")
    private Task task;
    @Id
    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @ManyToOne()
    @JoinColumn(name ="permission_id",referencedColumnName = "id")
    private Permission permission;
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}