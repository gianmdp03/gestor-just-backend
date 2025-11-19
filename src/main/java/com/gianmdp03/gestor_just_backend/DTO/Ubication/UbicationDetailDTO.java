package com.gianmdp03.gestor_just_backend.DTO.Ubication;

import com.gianmdp03.gestor_just_backend.DTO.InventoryItem.InventoryItemListDTO;

import java.util.List;

public record UbicationDetailDTO(Long id, String name, List<InventoryItemListDTO> inventoryItems) {
}
