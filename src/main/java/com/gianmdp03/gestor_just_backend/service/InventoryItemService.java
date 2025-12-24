package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryItemService {
    InventoryItemListDTO addInventoryItem(InventoryItemRequestDTO inventoryItemRequestDTO);
    InventoryItemListDTO updateInventoryItem(Long id, InventoryItemUpdateDTO dto);
    Page<InventoryItemListDTO> listInventoryItems(Pageable pageable);
    Page<InventoryItemListDTO> listInventoryItemsByProduct(Long productId, Pageable pageable);
    Page<InventoryItemListDTO> listInventoryItemsByLocation(Long locationId, Pageable pageable);
    Page<InventoryItemListDTO> listExpiredInventoryItems(Pageable pageable);
    void deleteInventoryItem(Long id);
}
