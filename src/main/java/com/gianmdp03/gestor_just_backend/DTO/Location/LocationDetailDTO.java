package com.gianmdp03.gestor_just_backend.dto.location;

import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemListDTO;

import java.util.List;

public record LocationDetailDTO(Long id, String name, List<InventoryItemListDTO> inventoryItems) {
}
