package org.example.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.todolist.model.composite_key.AssigneeId;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "assignees")
public class Assignee {
    @EmbeddedId
    private AssigneeId id;
    @ManyToOne()
    @JoinColumn(name ="permission_id",referencedColumnName = "id")
    private Permission permission;
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}