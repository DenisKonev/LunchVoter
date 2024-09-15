package ru.topjava.lunchvoter.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant extends BaseEntity {
    @NotBlank(message = "Name must not be blank")
    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Menu menu;
}