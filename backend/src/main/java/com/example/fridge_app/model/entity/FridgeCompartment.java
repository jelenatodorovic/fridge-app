package com.example.fridge_app.model.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fridge_compartment")
public class FridgeCompartment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "temp_from")
    private Double temperatureFrom;
    
    @Column(name = "temp_to")
    private Double temperatureTo;
    
    @Column(name = "capacity")
    private Double capacity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fridge_id", nullable = false)
    private Fridge fridge;
    
    @OneToMany(mappedBy = "compartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
    
    // Default constructor for JPA
    public FridgeCompartment() {
    }
    
    public FridgeCompartment(Double temperatureFrom, Double temperatureTo, Double capacity) {
        this.temperatureFrom = temperatureFrom;
        this.temperatureTo = temperatureTo;
        this.capacity = capacity;
    }
    
    // Getters and setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Double getTemperatureFrom() {
        return temperatureFrom;
    }
    
    public void setTemperatureFrom(Double temperatureFrom) {
        this.temperatureFrom = temperatureFrom;
    }
    
    public Double getTemperatureTo() {
        return temperatureTo;
    }
    
    public void setTemperatureTo(Double temperatureTo) {
        this.temperatureTo = temperatureTo;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }
    public Fridge getFridge() {
        return fridge;
    }
    
    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    // Helper methods
    public void addItem(Item item) {
        items.add(item);
        item.setCompartment(this);
    }
    
    public void removeItem(Item item) {
        items.remove(item);
        item.setCompartment(null);
    }
    
    @Override
    public String toString() {
        return "FridgeCompartment{" +
                "id=" + id +
                ", temperatureFrom=" + temperatureFrom +
                ", temperatureTo=" + temperatureTo +
                ", items=" + items.size() +
                '}';
    }
} 