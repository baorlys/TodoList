package org.example.todolist.enums;

import lombok.Getter;

@Getter
public enum PriorityType {
    LOW(1),
    MEDIUM(2),
    HIGH(3);
    private final Integer id;
    private PriorityType(final Integer id) {
        this.id = id;
    }
}
