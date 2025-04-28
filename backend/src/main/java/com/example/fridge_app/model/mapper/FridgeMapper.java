package com.example.fridge_app.model.mapper;

import com.example.fridge_app.model.dto.FridgeDto;
import com.example.fridge_app.model.dto.FridgeCompartmentDto;
import com.example.fridge_app.model.dto.CompartmentType;
import com.example.fridge_app.model.entity.Fridge;
import com.example.fridge_app.model.entity.FridgeCompartment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface FridgeMapper {
    FridgeMapper INSTANCE = Mappers.getMapper(FridgeMapper.class);

    @Mapping(target = "compartments", source = "compartments", qualifiedByName = "compartmentsListToMap")
    FridgeDto toDto(Fridge fridge);

    @Mapping(target = "compartments", source = "compartments", qualifiedByName = "compartmentsMapToList")
    @Mapping(target = "name", source = "name")
    Fridge toEntity(FridgeDto dto);

    @Named("compartmentsListToMap")
    default Map<CompartmentType, FridgeCompartmentDto> compartmentsListToMap(List<FridgeCompartment> compartments) {
        Map<CompartmentType, FridgeCompartmentDto> result = new HashMap<>();
        for (FridgeCompartment compartment : compartments) {
            // Determine the compartment type based on temperature range
            CompartmentType type = determineCompartmentType(compartment);
            FridgeCompartmentDto dto = new FridgeCompartmentDto(
                type,
                compartment.getTemperatureFrom(),
                compartment.getTemperatureTo(),
                compartment.getCapacity()
            );
            result.put(type, dto);
        }
        return result;
    }

    @Named("compartmentsMapToList")
    default List<FridgeCompartment> compartmentsMapToList(Map<CompartmentType, FridgeCompartmentDto> compartments) {
        List<FridgeCompartment> result = new ArrayList<>();
        for (Map.Entry<CompartmentType, FridgeCompartmentDto> entry : compartments.entrySet()) {
            FridgeCompartment compartment = new FridgeCompartment(
                entry.getValue().getTempFromTo()[0],
                entry.getValue().getTempFromTo()[1],
                entry.getValue().getCapacity()
            );
            result.add(compartment);
        }
        return result;
    }

    @Named("totalCapacity")
    default double totalCapacity(Map<CompartmentType, FridgeCompartmentDto> compartments) {
        return compartments.values().stream().mapToDouble(FridgeCompartmentDto::getCapacity).sum();
    }

    @Named("determineCompartmentType")
    default CompartmentType determineCompartmentType(FridgeCompartment compartment) {
        double tempFrom = compartment.getTemperatureFrom();
        if (tempFrom <= -18.0) {
            return CompartmentType.FREEZER;
        } else if (tempFrom <= 2.0) {
            return CompartmentType.FRESH_ZONE;
        } else {
            return CompartmentType.FRIDGE;
        }
    }
} 