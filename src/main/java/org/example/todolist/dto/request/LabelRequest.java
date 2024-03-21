package org.example.todolist.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabelRequest {
    private String title;
    private String color;
    private Integer todoId;
    private Integer userId;
}
