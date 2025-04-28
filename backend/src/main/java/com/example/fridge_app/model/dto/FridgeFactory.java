package com.example.fridge_app.model.dto;

import java.util.HashMap;
import java.util.Map;

public class FridgeFactory {
    
    public static FridgeDto createFridge(FridgeType type, FridgeSize size) {
        switch (type) {
            case BASIC:
                return createBasicFridge(size);
            case WITH_FREEZER:
                return createFridgeWithFreezer(size);
            case PREMIUM:
                return createPremiumFridge(size);
            default:
                throw new IllegalArgumentException("Unknown fridge type: " + type);
        }
    }
    
    public static FridgeDto createBasicFridge(FridgeSize size) {
        Map<CompartmentType, FridgeCompartmentDto> compartments = new HashMap<>();
        // Basic fridge with just a fridge compartment
        compartments.put(CompartmentType.FRIDGE, CompartmentFactory.createCompartment(CompartmentType.FRIDGE, size));
        return new FridgeDto("Basic Fridge", compartments);
    }
    
    public static FridgeDto createFridgeWithFreezer(FridgeSize size) {
        Map<CompartmentType, FridgeCompartmentDto> compartments = new HashMap<>();
        // Fridge compartment
        compartments.put(CompartmentType.FRIDGE, CompartmentFactory.createCompartment(CompartmentType.FRIDGE, size));
        // Freezer compartment
        compartments.put(CompartmentType.FREEZER, CompartmentFactory.createCompartment(CompartmentType.FREEZER, size));
        return new FridgeDto("Fridge with Freezer", compartments);
    }
    
    public static FridgeDto createPremiumFridge(FridgeSize size) {
        Map<CompartmentType, FridgeCompartmentDto> compartments = new HashMap<>();
        // Fridge compartment
        compartments.put(CompartmentType.FRIDGE, CompartmentFactory.createCompartment(CompartmentType.FRIDGE, size));
        // Freezer compartment
        compartments.put(CompartmentType.FREEZER, CompartmentFactory.createCompartment(CompartmentType.FREEZER, size));
        // Fresh zone compartment
        compartments.put(CompartmentType.FRESH_ZONE, CompartmentFactory.createCompartment(CompartmentType.FRESH_ZONE, size));
        return new FridgeDto("Premium Fridge", compartments);
    }
} 