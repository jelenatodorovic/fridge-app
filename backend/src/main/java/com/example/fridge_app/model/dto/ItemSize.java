package com.example.fridge_app.model.dto;

/**
 * Enum representing different sizes of items that can be stored in fridge compartments.
 * Each size has an associated capacity requirement.
 */
public enum ItemSize {
    XS(1.0),  // Extra Small - takes 1 unit of capacity
    S(5.0),   // Small - takes 5 units of capacity
    M(10.0),  // Medium - takes 10 units of capacity
    L(20.0),  // Large - takes 20 units of capacity
    XL(30.0); // Extra Large - takes 30 units of capacity
    
    private final double capacityRequirement;
    
    ItemSize(double capacityRequirement) {
        this.capacityRequirement = capacityRequirement;
    }
    
    /**
     * Returns the capacity requirement for this item size
     * @return the capacity requirement in the same units as compartment capacity
     */
    public double getCapacityRequirement() {
        return capacityRequirement;
    }
} 