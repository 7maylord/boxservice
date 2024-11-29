package com.example.boxservice.service;

import com.example.boxservice.exception.ValidationException;
import com.example.boxservice.model.Box;
import com.example.boxservice.model.BoxState;
import com.example.boxservice.model.Item;
import com.example.boxservice.repository.BoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {

    private final BoxRepository boxRepository;

    public BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    public Box createBox(Box box) {
        if (box.getWeightLimit() > 500) throw new ValidationException("Weight limit cannot exceed 500 grams");
        if (box.getBatteryCapacity() < 0 || box.getBatteryCapacity() > 100)
            throw new ValidationException("Battery capacity must be between 0 and 100");
        return boxRepository.save(box);
    }

    public void loadItems(Long boxId, List<Item> items) {
        Box box = boxRepository.findById(boxId).orElseThrow(() -> new ValidationException("Box not found"));

        if (box.getBatteryCapacity() < 25) {
            throw new ValidationException("Battery level too low to load items");
        }

        if (box.getState() != BoxState.LOADING) {
            throw new ValidationException("Box must be in LOADING state to add items");
        }

        int totalWeight = items.stream().mapToInt(Item::getWeight).sum();
        if (totalWeight > box.getWeightLimit()) {
            throw new ValidationException("Items exceed the weight limit of the box");
        }

        box.getItems().addAll(items);
        boxRepository.save(box);
    }

    public List<Item> getItems(Long boxId) {
        Box box = boxRepository.findById(boxId).orElseThrow(() -> new ValidationException("Box not found"));
        return box.getItems();
    }

    public List<Box> getAvailableBoxes() {
        return boxRepository.findByState(BoxState.IDLE);
    }
}

