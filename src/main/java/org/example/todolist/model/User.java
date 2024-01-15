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
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 2, max = 30, message = "Invalid Name: Must be of 2 - 30 characters")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Invalid Password: Empty password")
    @NotNull(message = "Invalid Password: Password is NULL")
    @Size(min = 8, max = 30, message = "Invalid Password: Must be of 8 - 30 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Invalid Password: Must contain at least " +
            "1 uppercase, 1 lowercase and 1 number")
    private String password;

    @Column(name = "email")
    @NotBlank(message = "Invalid Email: Empty email")
    @NotNull(message = "Invalid Email: Email is NULL")
    @Email(message = "Invalid email")
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

}