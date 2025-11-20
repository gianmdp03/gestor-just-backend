package com.gianmdp03.gestor_just_backend.Mapper;

import com.gianmdp03.gestor_just_backend.DTO.InventoryItem.InventoryItemListDTO;
import com.gianmdp03.gestor_just_backend.DTO.InventoryItem.InventoryItemRequestDTO;
import com.gianmdp03.gestor_just_backend.Model.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "ubication", ignore = true)
    InventoryItem toEntity(InventoryItemRequestDTO dto);
    InventoryItemListDTO toDto(InventoryItem entity);
    List<InventoryItemListDTO> toListDto(List<InventoryItem> entities);
}
