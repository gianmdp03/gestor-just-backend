package com.gianmdp03.gestor_just_backend.Mapper;

import com.gianmdp03.gestor_just_backend.DTO.Location.LocationDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Location.LocationRequestDTO;
import com.gianmdp03.gestor_just_backend.Model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InventoryItemMapper.class})
public interface LocationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryItems", ignore = true)
    Location toEntity(LocationRequestDTO dto);
    LocationDetailDTO toDetailDto(Location entity);
    LocationListDTO toListDto(Location entity);

}
