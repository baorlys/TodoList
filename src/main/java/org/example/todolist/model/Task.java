package org.example.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Timestamp estimation;

    @Column(name = "`order`")
    private Timestamp order;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}