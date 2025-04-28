package com.example.fridge_app.service.impl;

import com.example.fridge_app.model.dto.FridgeDto;
import com.example.fridge_app.model.dto.FridgeFactory;
import com.example.fridge_app.model.dto.FridgeType;
import com.example.fridge_app.model.dto.FridgeSize;
import com.example.fridge_app.model.dto.ItemDto;
import com.example.fridge_app.model.entity.Fridge;
import com.example.fridge_app.model.entity.FridgeCompartment;
import com.example.fridge_app.model.entity.Item;
import com.example.fridge_app.model.mapper.FridgeMapper;
import com.example.fridge_app.model.mapper.ItemMapper;
import com.example.fridge_app.repository.FridgeCompartmentRepository;
import com.example.fridge_app.repository.FridgeRepository;
import com.example.fridge_app.repository.ItemRepository;
import com.example.fridge_app.service.FridgeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class FridgeServiceImpl implements FridgeService {

    @Autowired
    private FridgeRepository fridgeRepository;

    @Autowired
    private FridgeCompartmentRepository compartmentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public FridgeDto createFridgeByTypeAndSize(FridgeType type, FridgeSize size) {
        FridgeDto fridgeDto = FridgeFactory.createFridge(type, size);
        Fridge fridge = FridgeMapper.INSTANCE.toEntity(fridgeDto);
        
        for (FridgeCompartment compartment : fridge.getCompartments()) {
            compartment.setFridge(fridge);
        }

        fridgeRepository.save(fridge);
        
        return fridgeDto;
    }

    @Override
    public ItemDto addItemToFridge(ItemDto itemDto, int fridgeId, double storeAtTemperature) {
        Fridge fridge = fridgeRepository.findById(fridgeId);
        if (fridge == null) {
            return null;
        }

        // Find a compartment that can store the item at the specified temperature
        FridgeCompartment compartment = compartmentRepository.findCompartmentByFridgeIdAndTemperature(fridgeId, storeAtTemperature);
        if (compartment == null) {
            return null; // No suitable compartment found
        }
        
        // Check if the compartment has enough capacity
        double currentOccupiedCapacity = compartment.getItems().stream()
                .mapToDouble(Item::getCapacityRequirement)
                .sum();
                
        if (currentOccupiedCapacity + itemDto.getCapacityRequirement() > compartment.getCapacity()) {
            return null; // Not enough capacity
        }
        
        // Create and save the item
        Item newItem = new Item();
        newItem.setName(itemDto.getName());
        newItem.setType(itemDto.getSize()); // Store the size enum name as the type
        newItem.setCapacityRequirement(itemDto.getCapacityRequirement());
        newItem.setCompartment(compartment);
        newItem.setTimeStored(LocalDate.now());
        newItem.setBestBeforeDate(itemDto.getBestBeforeDate());
        newItem.setCompartment(compartment);
        
        // Add the item to the compartment
        compartment.getItems().add(newItem);
        compartmentRepository.save(compartment);
        
        // Update the item DTO with the compartment and fridge IDs
        itemDto.setId(newItem.getId());
        itemDto.setFridgeId(fridgeId);
        
        return itemDto;
    }

    @Override
    public ItemDto removeItemFromFridge(int itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isEmpty()) { return null; }

        itemRepository.delete(item.get());
        return ItemMapper.INSTANCE.toDto(item.get());
    }

    @Override
    public void removeExpiredItems(int fridgeId) {
        Fridge fridge = fridgeRepository.findById(fridgeId);
        if (fridge == null) {
            return;
        }

        for (FridgeCompartment compartment : fridge.getCompartments()) {
            // Create a list of items to remove to avoid ConcurrentModificationException
            List<Item> itemsToRemove = new ArrayList<>();
            
            for (Item item : compartment.getItems()) {
                if (item.getBestBeforeDate() != null && item.getBestBeforeDate().isBefore(LocalDate.now())) {
                    itemsToRemove.add(item);
                }
            }
            
            // Remove the items after the iteration is complete
            for (Item item : itemsToRemove) {
                itemRepository.delete(item);
                compartment.getItems().remove(item);
            }
        }
        // Save the updated compartments
        compartmentRepository.saveAll(fridge.getCompartments());
    }

    @Override
    public List<FridgeDto> getAllFridges() {
        List<Fridge> fridges = fridgeRepository.findAll();
        return fridges.stream()
                .map(FridgeMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    public List<ItemDto> getAllItems(int fridgeId) {
        Fridge fridge = fridgeRepository.findById(fridgeId);
        if (fridge == null) {
            return null;
        }
        
        return fridge.getCompartments().stream()
                .flatMap(compartment -> compartment.getItems().stream())
                .map(item -> {
                    // Create a new ItemDto with the required parameters
                    ItemDto dto = new ItemDto(
                        item.getName(),
                        item.getType(),
                        item.getBestBeforeDate()
                    );
                    
                    // Set the IDs
                    dto.setId(item.getId());
                    dto.setFridgeId(fridgeId);
                    
                    return dto;
                })
                .toList();
    }
}
