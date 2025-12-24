package com.gianmdp03.gestor_just_backend.mapper;

import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemUpdateDTO;
import com.gianmdp03.gestor_just_backend.model.InventoryItem;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class InventoryItemMapper {
    @Autowired
    @Lazy
    private ProductMapper productMapper;

    @Autowired
    @Lazy
    private LocationMapper locationMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "location", ignore = true)
    public abstract InventoryItem toEntity(InventoryItemRequestDTO dto);
    public abstract InventoryItemListDTO toListDto(InventoryItem entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "location", ignore = true)
    public abstract void updateEntityFromDto(InventoryItemUpdateDTO dto, @MappingTarget InventoryItem entity);
}
