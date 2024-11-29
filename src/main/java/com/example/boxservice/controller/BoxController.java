package com.example.boxservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boxservice.model.Box;
import com.example.boxservice.model.Item;
import com.example.boxservice.service.BoxService;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {

    private final BoxService boxService;

    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @PostMapping
    public ResponseEntity<Box> createBox(@RequestBody Box box) {
        return ResponseEntity.ok(boxService.createBox(box));
    }

    @PutMapping("/{id}/load")
    public ResponseEntity<Void> loadItems(@PathVariable Long id, @RequestBody List<Item> items) {
        boxService.loadItems(id, items);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<Item>> getItems(@PathVariable Long id) {
        return ResponseEntity.ok(boxService.getItems(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Box>> getAvailableBoxes() {
        return ResponseEntity.ok(boxService.getAvailableBoxes());
    }
}

