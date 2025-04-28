package com.example.fridge_app.model.dto;

import java.util.ArrayList;
import java.util.List;

public class FridgeCompartmentDto {
    private int id;
    private double[] tempFromTo;
    private double capacity;
    private CompartmentType type;
    private List<ItemDto> items;

    public FridgeCompartmentDto(CompartmentType type, double tempFrom, double tempTo, double capacity) {
        this.tempFromTo = new double[] {tempFrom, tempTo};
        this.capacity = capacity;
        this.type = type;
        items = new ArrayList<>();
    }

    public boolean isFull() {
        double usedCapacity = items.stream()
                .mapToDouble(ItemDto::getCapacityRequirement)
                .sum();
        double availableCapacity = capacity - usedCapacity;
        return availableCapacity < 0.001;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getCapacity() {
        return capacity;
    }
    
    public double getUsedCapacity() {
        return items.stream()
                .mapToDouble(ItemDto::getCapacityRequirement)
                .sum();
    }
    
    public double getAvailableCapacity() {
        return capacity - getUsedCapacity();
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public double[] getTempFromTo() {
        return tempFromTo;
    }
    
    public CompartmentType getType() {
        return type;
    }
    
    /**
     * Adds an item to this compartment if there's enough capacity
     * @param item the item to add
     * @return true if the item was added, false if there wasn't enough capacity
     */
    public boolean addItem(ItemDto item) {
        if (item == null) {
            return false;
        }
        
        double itemCapacity = item.getCapacityRequirement();
        if (getAvailableCapacity() >= itemCapacity) {
            items.add(item);
            return true;
        }
        
        return false;
    }
}
