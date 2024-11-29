package com.example.boxservice.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String txref; // Unique reference, max 20 characters

    @Column(nullable = false)
    private int weightLimit; // Max weight in grams (500 max)

    @Column(nullable = false)
    private int batteryCapacity; // Battery percentage (0-100)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoxState state; // Box state (e.g., IDLE, LOADING,LOADED, DELIVERING, DELIVERED, RETURNING)

    @OneToMany(mappedBy = "box", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
}
