package com.example.fridge_app.controller;

import com.example.fridge_app.model.dto.FridgeDto;
import com.example.fridge_app.model.dto.FridgeType;
import com.example.fridge_app.model.dto.ItemDto;
import com.example.fridge_app.model.dto.FridgeSize;
import com.example.fridge_app.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/fridges")
public class FridgeController {
    private static final Logger logger = LoggerFactory.getLogger(FridgeController.class);

    @Autowired
    private FridgeService fridgeService;
    
    @PostMapping("/type/{type}/size/{size}")
    public ResponseEntity<FridgeDto> createFridgeByTypeAndSize(@PathVariable FridgeType type, @PathVariable FridgeSize size) {
        logger.info("Received request to create fridge of type: {} and size: {}", type, size);
        try {
            FridgeDto fridge = fridgeService.createFridgeByTypeAndSize(type, size);
            logger.info("Successfully created fridge: {}", fridge);
            return ResponseEntity.ok(fridge);
        } catch (Exception e) {
            logger.error("Error creating fridge: ", e);
            throw e;
        }
    }

    @PostMapping("/{fridgeId}/items")
    public ResponseEntity<ItemDto> addItemToFridge(
            @PathVariable int fridgeId,
            @RequestParam double temperature,
            @RequestBody ItemDto itemDto) {
        
        ItemDto addedItem = fridgeService.addItemToFridge(itemDto, fridgeId, temperature);
        if (addedItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addedItem);
    }
    
    @GetMapping
    public ResponseEntity<List<FridgeDto>> getAllFridges() {
        logger.info("Received request to get all fridges");
        List<FridgeDto> fridges = fridgeService.getAllFridges();
        return ResponseEntity.ok(fridges);
    }

    @GetMapping("/{fridgeId}/items")
    public ResponseEntity<List<ItemDto>> getAllItems(@PathVariable int fridgeId) {
        List<ItemDto> items = fridgeService.getAllItems(fridgeId);
        if (items == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{fridgeId}/items/removeExpired")
    public ResponseEntity<Void> removeExpiredItems(@PathVariable int fridgeId) {
        logger.info("Received request to remove expired items from fridge with ID: {}", fridgeId);
        fridgeService.removeExpiredItems(fridgeId);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItemFromFridge(@PathVariable int itemId) {
        logger.info("Received request to remove item with ID: {}", itemId);
        ItemDto removedItem = fridgeService.removeItemFromFridge(itemId);
        if (removedItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
