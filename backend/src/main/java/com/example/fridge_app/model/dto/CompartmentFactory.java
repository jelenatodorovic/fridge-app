package com.example.fridge_app.model.dto;

/**
 * Factory class for creating fridge compartments based on size
 */
public class CompartmentFactory {
    
    /**
     * Creates a fridge compartment with appropriate capacity based on size
     * 
     * @param type The type of compartment (FRIDGE, FREEZER, FRESH_ZONE)
     * @param size The size of the fridge (S, M, L)
     * @return A FridgeCompartmentDto with appropriate capacity
     */
    public static FridgeCompartmentDto createCompartment(CompartmentType type, FridgeSize size) {
        double capacity = getCapacityForTypeAndSize(type, size);
        
        switch (type) {
            case FRIDGE:
                return new FridgeCompartmentDto(type, 4.0, 8.0, capacity);
            case FREEZER:
                return new FridgeCompartmentDto(type, -22.0, -18.0, capacity);
            case FRESH_ZONE:
                return new FridgeCompartmentDto(type, 0.0, 2.0, capacity);
            default:
                throw new IllegalArgumentException("Unknown compartment type: " + type);
        }
    }
    
    /**
     * Returns the appropriate capacity for a given compartment type and fridge size
     */
    private static double getCapacityForTypeAndSize(CompartmentType type, FridgeSize size) {
        switch (type) {
            case FRIDGE:
                switch (size) {
                    case S: return 60.0;
                    case M: return 100.0;
                    case L: return 150.0;
                    default: throw new IllegalArgumentException("Unknown fridge size: " + size);
                }
            case FREEZER:
                switch (size) {
                    case S: return 30.0;
                    case M: return 50.0;
                    case L: return 80.0;
                    default: throw new IllegalArgumentException("Unknown fridge size: " + size);
                }
            case FRESH_ZONE:
                switch (size) {
                    case S: return 20.0;
                    case M: return 30.0;
                    case L: return 50.0;
                    default: throw new IllegalArgumentException("Unknown fridge size: " + size);
                }
            default:
                throw new IllegalArgumentException("Unknown compartment type: " + type);
        }
    }
} 