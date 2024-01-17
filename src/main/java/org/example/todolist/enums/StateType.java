package org.example.todolist.enums;

public enum StateType {
    TODO(1),
    DOING(2),
    DONE(3);

    private Integer typeId;

    private StateType(final Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeId() {
        return typeId;
    }
}
