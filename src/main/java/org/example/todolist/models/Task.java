package org.example.todolist.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todolist_id")
    private Todolist todolist;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "assignee_id", referencedColumnName = "id")
    private Assignee assignee;

    @ManyToOne()
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne()
    @JoinColumn(name = "state_id")
    private State state;

    @Column(name = "estimation")
    private Instant estimation;

    @Column(name = "`order`")
    private Integer order;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}