package com.example.fridge_app.model.dto;

import java.util.Map;
import java.util.Collections;

public class FridgeDto {
    private Integer id;
    private String name;
    private double totalCapacity;
    private Map<CompartmentType, FridgeCompartmentDto> compartments;

    public FridgeDto(String name, Map<CompartmentType, FridgeCompartmentDto> compartments) {
        this.name = name;
        this.compartments = Map.copyOf(compartments);
        totalCapacity = compartments.values().stream().mapToDouble(FridgeCompartmentDto::getCapacity).sum();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmpty() {
        return compartments.values().stream().allMatch(FridgeCompartmentDto::isEmpty);
    }

    public boolean isFull() {
        return compartments.values().stream().allMatch(FridgeCompartmentDto::isFull);
    }

    public Map<CompartmentType, FridgeCompartmentDto> getCompartments() {
        return Collections.unmodifiableMap(compartments);
    }
    
    public double getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(double totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    @Override
    public String toString() {
        return "Fridge " + name + " with total capacity " + totalCapacity;
    }
}
