package com.gianmdp03.gestor_just_backend.mapper;

import com.gianmdp03.gestor_just_backend.dto.location.LocationDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationUpdateDTO;
import com.gianmdp03.gestor_just_backend.model.Location;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {InventoryItemMapper.class})
public abstract class LocationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryItems", ignore = true)
    public abstract Location toEntity(LocationRequestDTO dto);
    public abstract LocationDetailDTO toDetailDto(Location entity);
    public abstract LocationListDTO toListDto(Location entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryItems", ignore = true)
    public abstract void updateEntityFromDto(LocationUpdateDTO dto, @MappingTarget Location entity);
}
