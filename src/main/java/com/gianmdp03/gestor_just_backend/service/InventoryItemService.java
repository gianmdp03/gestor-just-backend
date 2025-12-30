package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface InventoryItemService {
    InventoryItemListDTO addInventoryItem(InventoryItemRequestDTO inventoryItemRequestDTO);
    InventoryItemListDTO updateInventoryItem(Long id, InventoryItemUpdateDTO dto);
    Page<InventoryItemListDTO> listInventoryItems(LocalDate localDate, Pageable pageable);
    Page<InventoryItemListDTO> listInventoryItemsByProduct(LocalDate localDate, Long productId, Pageable pageable);
    Page<InventoryItemListDTO> listInventoryItemsByLocation(LocalDate localDate, Long locationId, Pageable pageable);
    Page<InventoryItemListDTO> listExpiringSoonInventoryItems(LocalDate localDate, int days, Pageable pageable);
    Page<InventoryItemListDTO> listExpiredInventoryItems(LocalDate localDate, Pageable pageable);
    InventoryItemListDTO getInventoryItemById(Long id);
    void deleteInventoryItem(Long id);
}
