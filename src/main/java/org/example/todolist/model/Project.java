package org.example.todolist.model;

import jakarta.persistence.*;
import jdk.jfr.Label;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "title")
    private String title;

    @Column(name = "from_date")
    private Timestamp fromDate;

    @Column(name = "to_date")
    private Timestamp toDate;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<TodoList> todoLists;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;



    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "is_hidden", columnDefinition = "boolean default false")
    private Boolean isHidden;


}
