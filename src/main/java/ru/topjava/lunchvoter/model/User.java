package ru.topjava.lunchvoter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
    @NotBlank(message = "Username must not be blank")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Role must not be blank")
    @Column(nullable = false)
    private String role;
}