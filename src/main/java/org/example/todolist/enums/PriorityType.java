package org.example.todolist.enums;

public enum PriorityType {
    LOW(1),
    MEDIUM(2),
    HIGH(3);
    private Integer id;
    private PriorityType(final Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}
