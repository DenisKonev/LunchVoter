package ru.topjava.lunchvoter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
public class MenuItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonBackReference
    private Menu menu;

    @NotBlank(message = "Name must not be blank")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Price must not be null")
    @Column(nullable = false)
    private BigDecimal price;
}