package com.example.boxservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boxservice.model.Box;
import com.example.boxservice.model.BoxState;

public interface BoxRepository extends JpaRepository<Box, Long> {
    List<Box> findByState(BoxState state);
}
