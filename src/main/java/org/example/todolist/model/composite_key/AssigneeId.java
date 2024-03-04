package org.example.todolist.model.composite_key;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.todolist.model.TodoList;
import org.example.todolist.model.User;

import java.io.Serial;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable

public class AssigneeId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private TodoList todoList;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
