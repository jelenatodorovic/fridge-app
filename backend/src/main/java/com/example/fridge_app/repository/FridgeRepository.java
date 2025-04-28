package com.example.fridge_app.repository;

import com.example.fridge_app.model.entity.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FridgeRepository extends JpaRepository<Fridge, Integer> {
    Fridge findById(int id);

} 