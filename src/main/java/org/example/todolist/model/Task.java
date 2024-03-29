package org.example.todolist.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "todolist_id")
    private TodoList todolist;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name= "is_completed", columnDefinition = "boolean default false")

    private Boolean isCompleted;

    @ManyToOne()
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne()
    @JoinColumn(name = "state_id")
    private State state;

    @Column(name = "estimation")
    private Timestamp estimation;

    @Column(name = "`order`")
    private Integer order;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "is_hidden", columnDefinition = "boolean default false")
    private Boolean isHidden;

}