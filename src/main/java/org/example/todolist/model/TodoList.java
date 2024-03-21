package org.example.todolist.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "todolists")
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "estimation")
    private Timestamp estimation;

    @Column(name = "`order`")
    private Integer order;

    @ManyToOne()
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne()
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "todolist_labels",
            joinColumns = @JoinColumn(name = "todolist_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> labels;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;

    public void addLabel(Label label) {
        this.labels.add(label);
        label.getTodoLists().add(this);
    }

    public void removeLabel(Integer labelId) {
        Label label = this.labels.stream().filter(t -> t.getId().equals(labelId)).findFirst().orElse(null);
        if (label != null) {
            this.labels.remove(label);
            label.getTodoLists().remove(this);
        }
    }

}