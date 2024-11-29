package com.example.boxservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boxservice.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {}
