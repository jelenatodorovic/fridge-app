package com.example.fridge_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fridge_app.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
