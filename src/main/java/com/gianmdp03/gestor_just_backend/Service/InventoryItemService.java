package com.gianmdp03.gestor_just_backend.Service;

import com.gianmdp03.gestor_just_backend.DTO.InventoryItem.InventoryItemListDTO;
import com.gianmdp03.gestor_just_backend.DTO.InventoryItem.InventoryItemRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryItemService {
    InventoryItemListDTO addInventoryItem(InventoryItemRequestDTO inventoryItemRequestDTO);
    Page<InventoryItemListDTO> listInventoryItems(Pageable pageable);
    Page<InventoryItemListDTO> listInventoryItemsByProduct(Long productId, Pageable pageable);
    Page<InventoryItemListDTO> listInventoryItemsByLocation(Long locationId, Pageable pageable);
    Page<InventoryItemListDTO> listNonExpiredInventoryItems(Pageable pageable);
    Page<InventoryItemListDTO> listExpiredInventoryItems(Pageable pageable);
    void deleteInventoryItem(Long id);
}
