package com.example.fridge_app.service;

import com.example.fridge_app.model.dto.FridgeDto;
import com.example.fridge_app.model.dto.ItemDto;
import com.example.fridge_app.model.dto.FridgeType;
import com.example.fridge_app.model.dto.FridgeSize;
import java.util.List;

public interface FridgeService {
    FridgeDto createFridgeByTypeAndSize(FridgeType type, FridgeSize size);
    ItemDto addItemToFridge(ItemDto itemdto, int fridgeId, double storeAtTemperature);
    ItemDto removeItemFromFridge(int itemId);
    void removeExpiredItems(int fridgeId);
    List<FridgeDto> getAllFridges();
    List<ItemDto> getAllItems(int fridgeId);
}
