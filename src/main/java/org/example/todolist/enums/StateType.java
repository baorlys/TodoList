package org.example.todolist.enums;

import lombok.Getter;

@Getter
public enum StateType {
    TODO(1),
    DOING(2),
    DONE(3);

    private final Integer typeId;

    private StateType(final Integer typeId) {
        this.typeId = typeId;
    }

}
