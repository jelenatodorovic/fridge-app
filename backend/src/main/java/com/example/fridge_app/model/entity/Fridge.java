package com.example.fridge_app.model.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fridge")
public class Fridge {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "fridge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FridgeCompartment> compartments = new ArrayList<>();
    
    // Default constructor for JPA
    public Fridge() {
    }
    
    public Fridge(String name) {
        this.name = name;
    }
    
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
    
    public List<FridgeCompartment> getCompartments() {
        return compartments;
    }
    
    public void setCompartments(List<FridgeCompartment> compartments) {
        this.compartments = compartments;
    }
    
    @Override
    public String toString() {
        return "Fridge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", compartments=" + compartments.size() +
                '}';
    }
} 