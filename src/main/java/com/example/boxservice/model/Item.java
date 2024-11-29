package com.example.boxservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Invalid item name")
    @Column(nullable = false)
    private String name; // Allowed: letters, numbers, '-', '_'

    @Column(nullable = false)
    private int weight; // Weight of the item in grams

    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Invalid item code")
    @Column(nullable = false, unique = true)
    private String code; // Allowed: uppercase letters, numbers, '_'

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box; // Reference to the associated Box
}
