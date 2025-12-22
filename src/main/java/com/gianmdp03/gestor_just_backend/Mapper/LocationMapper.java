package com.gianmdp03.gestor_just_backend.mapper;

import com.gianmdp03.gestor_just_backend.dto.location.LocationDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationRequestDTO;
import com.gianmdp03.gestor_just_backend.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {InventoryItemMapper.class})
public interface LocationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryItems", ignore = true)
    Location toEntity(LocationRequestDTO dto);
    LocationDetailDTO toDetailDto(Location entity);
    LocationListDTO toListDto(Location entity);

}
