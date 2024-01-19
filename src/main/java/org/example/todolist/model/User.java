package org.example.todolist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username")

    private String username;


    private String password;

    @Column(name = "email")

    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "mobile_hidden")
    private Boolean mobileHidden;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "lastlogin_at")
    private Timestamp lastloginAt;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "is_hidden")
    private Boolean isHidden;
}