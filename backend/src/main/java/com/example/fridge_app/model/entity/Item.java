package com.example.fridge_app.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.example.fridge_app.model.dto.ItemSize;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemSize type;

    @Column(nullable = false)
    private double capacityRequirement;
    
    @Column(name = "time_stored")
    @Temporal(TemporalType.DATE)
    private LocalDate timeStored;
    
    @Column(name = "best_before_date")
    @Temporal(TemporalType.DATE)
    private LocalDate bestBeforeDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fridge_compartment_id", nullable = false)
    private FridgeCompartment compartment;
    
    // Getters and setters
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

    public ItemSize getType() {
        return type;
    }
    
    public void setType(ItemSize type) {
        this.type = type;
    }
    
    public LocalDate getTimeStored() {
        return timeStored;
    }
    
    public void setTimeStored(LocalDate timeStored) {
        this.timeStored = timeStored;
    }
    
    public LocalDate getBestBeforeDate() {
        return bestBeforeDate;
    }
    
    public void setBestBeforeDate(LocalDate bestBeforeDate) {
        this.bestBeforeDate = bestBeforeDate;
    }
    
    public FridgeCompartment getCompartment() {
        return compartment;
    }
    
    public void setCompartment(FridgeCompartment compartment) {
        this.compartment = compartment;
    }
    
    public double getCapacityRequirement() {
        return capacityRequirement;
    }
    
    public void setCapacityRequirement(double capacityRequirement) {
        this.capacityRequirement = capacityRequirement;
    }
} 