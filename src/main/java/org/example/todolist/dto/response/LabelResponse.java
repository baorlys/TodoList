package org.example.todolist.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabelResponse {
    private Integer id;
    private String title;
    private String color;
}
