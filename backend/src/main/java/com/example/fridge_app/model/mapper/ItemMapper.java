package com.example.fridge_app.model.mapper;

import com.example.fridge_app.model.dto.ItemDto;
import com.example.fridge_app.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    
    ItemDto toDto(Item item);
    
    Item toEntity(ItemDto dto);
} 