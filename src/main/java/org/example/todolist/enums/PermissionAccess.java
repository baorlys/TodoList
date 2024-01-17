package org.example.todolist.enums;

public enum PermissionAccess {
    VIEW(1),
    EDIT(2);

    private final Integer id;

    PermissionAccess(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static PermissionAccess getById(Integer id) {
        for(PermissionAccess e : values()) {
            if(e.id.equals(id)) return e;
        }
        return null;
    }
}
