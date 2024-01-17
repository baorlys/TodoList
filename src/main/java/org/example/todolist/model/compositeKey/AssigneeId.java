package org.example.todolist.model.compositeKey;

import org.example.todolist.model.Task;
import org.example.todolist.model.User;

import java.io.Serializable;


public class AssigneeId implements Serializable {
    private Task task;
    private User user;
}
