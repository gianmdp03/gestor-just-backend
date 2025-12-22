package com.gianmdp03.gestor_just_backend.dto.inventoryitem;

import com.gianmdp03.gestor_just_backend.model.Product;
import com.gianmdp03.gestor_just_backend.model.Location;

import java.time.LocalDate;

public record InventoryItemListDTO(Long id, Product product, Location location, int stock, LocalDate expireDate) {
}
