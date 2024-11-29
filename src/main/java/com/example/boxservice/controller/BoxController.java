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
    //Create a Box
    @PostMapping
    public ResponseEntity<Box> createBox(@RequestBody Box box) {
        return ResponseEntity.ok(boxService.createBox(box));
    }
    //Load item into a Box
    @PutMapping("/{id}/load")
    public ResponseEntity<Box> loadItems(@PathVariable Long id, @RequestBody List<Item> items) {
        try {
            Box updatedBox = boxService.loadItems(id, items);
            return ResponseEntity.ok(updatedBox);
        } catch (IllegalStateException ex) {
            return ResponseEntity.badRequest().body(null); // Send 400 Bad Request if validation fails
        }
    }
    //Get Items in a Box
    @GetMapping("/{id}/items")
    public ResponseEntity<List<Item>> getItems(@PathVariable Long id) {
        return ResponseEntity.ok(boxService.getItems(id));
    }
    //Get Box Details
    @GetMapping("/available")
    public ResponseEntity<List<Box>> getAvailableBoxes() {
        return ResponseEntity.ok(boxService.getAvailableBoxes());
    }
}

