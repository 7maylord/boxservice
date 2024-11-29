package com.example.boxservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.boxservice.exception.ValidationException;
import com.example.boxservice.model.Box;
import com.example.boxservice.model.BoxState;
import com.example.boxservice.model.Item;
import com.example.boxservice.repository.BoxRepository;
import com.example.boxservice.repository.ItemRepository;

@Service
public class BoxService {

    private final BoxRepository boxRepository;
    private final ItemRepository itemRepository;

    public BoxService(BoxRepository boxRepository, ItemRepository itemRepository) {
        this.boxRepository = boxRepository;
        this.itemRepository = itemRepository;
    }

    public Box createBox(Box box) {
        if (box.getWeightLimit() > 500) throw new ValidationException("Weight limit cannot exceed 500 grams");
        if (box.getBatteryCapacity() < 0 || box.getBatteryCapacity() > 100)
            throw new ValidationException("Battery capacity must be between 0 and 100");
        return boxRepository.save(box);
    }

    public Box loadItems(Long boxId, List<Item> items) {
        Box box = boxRepository.findById(boxId).orElseThrow(() -> new ValidationException("Box not found"));
        
        // Check if battery level is below 25%
        if (box.getBatteryCapacity() < 25) {
            throw new ValidationException("Battery level too low to load items");
        }
    
        // Persist each item before adding to the box
        for (Item item : items) {
            if (item.getId() == null) { // Only save the item if it's not already saved
                itemRepository.save(item); // Ensure itemRepository is available
            }
        }
    
        // Calculate the total weight of the items to be added
        int totalWeight = items.stream().mapToInt(Item::getWeight).sum();
    
        // Check if the total weight exceeds the box's weight limit
        if (box.getWeightLimit() - totalWeight < 0) {
            throw new ValidationException("Items exceed the weight limit of the box");
        }
    
        box.getItems().addAll(items);
        box.setState(BoxState.LOADING); 
    
        // If the total weight exceeds or matches the box's weight limit, change the state to LOADED
        if (box.getItems().stream().mapToInt(Item::getWeight).sum() >= box.getWeightLimit()) {
            box.setState(BoxState.LOADED);
        } else {
            box.setState(BoxState.LOADING);  // Set state to LOADING if items are being loaded
        }
    
        // Save the box and return the updated box
        return boxRepository.save(box);
    }
    

    public List<Item> getItems(Long boxId) {
        Box box = boxRepository.findById(boxId).orElseThrow(() -> new ValidationException("Box not found"));
        return box.getItems();
    }

    public List<Box> getAvailableBoxes() {
        return boxRepository.findByState(BoxState.IDLE);
    }

    public int getBatteryCapacity(Long boxId) {
        Box box = boxRepository.findById(boxId)
            .orElseThrow(() -> new ValidationException("Box not found"));
        return box.getBatteryCapacity();
    }
}

