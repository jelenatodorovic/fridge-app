package com.example.fridge_app.model.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ItemDto {
    
    private Integer id;
    
    @JsonIgnore
    private Integer fridgeId;
    
    private LocalDate bestBeforeDate;
    private ItemSize size;
    private String name;

    public ItemDto(String name, ItemSize size, LocalDate bestBeforeDate) {
        this.name = name;
        this.size = size;
        this.bestBeforeDate = bestBeforeDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(Integer fridgeId) {
        this.fridgeId = fridgeId;
    }

    public LocalDate getBestBeforeDate() {  
        return bestBeforeDate;
    }

    public ItemSize getSize() {
        return size;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Returns the capacity requirement for this item based on its size
     * @return the capacity requirement in the same units as compartment capacity
     */
    public double getCapacityRequirement() {
        return size != null ? size.getCapacityRequirement() : 0.0;
    }
}
