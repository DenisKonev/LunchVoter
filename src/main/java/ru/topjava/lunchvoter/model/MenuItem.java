package ru.topjava.lunchvoter.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    // TODO Getters and Setters
}