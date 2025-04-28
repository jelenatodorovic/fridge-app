package com.example.fridge_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fridge_app.model.entity.FridgeCompartment;

public interface FridgeCompartmentRepository extends JpaRepository<FridgeCompartment, Integer> {

    @Query("SELECT c FROM FridgeCompartment c join Fridge f on c.fridge.id = f.id " + 
            "WHERE f.id = :fridgeId AND :temperature >= c.temperatureFrom AND :temperature <= c.temperatureTo")
    FridgeCompartment findCompartmentByFridgeIdAndTemperature(int fridgeId, double temperature);
    
}
