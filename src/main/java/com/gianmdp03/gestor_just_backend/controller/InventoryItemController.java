package com.gianmdp03.gestor_just_backend.controller;

import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemUpdateDTO;
import com.gianmdp03.gestor_just_backend.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/inventory-items")
@RequiredArgsConstructor
public class InventoryItemController {
    private final InventoryItemService inventoryItemService;

    @PostMapping
    public ResponseEntity<InventoryItemListDTO> addInventoryItem(@Valid @RequestBody InventoryItemRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryItemService.addInventoryItem(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryItemListDTO> updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItemUpdateDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.updateInventoryItem(id, dto));
    }

    @GetMapping("/{localDate}")
    public ResponseEntity<Page<InventoryItemListDTO>> listInventoryItems(@PathVariable LocalDate localDate,
            @PageableDefault(page = 0, size = 10, sort = "expireDate", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.listInventoryItems(localDate, pageable));
    }
    @GetMapping("/{localDate}/product/{productId}")
    public ResponseEntity<Page<InventoryItemListDTO>> listInventoryItemsByProduct(@PathVariable LocalDate localDate, @PathVariable Long productId,
            @PageableDefault(page = 0, size = 10, sort = "expireDate", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.listInventoryItemsByProduct(localDate, productId, pageable));
    }
    @GetMapping("/{localDate}/location/{locationId}")
    public ResponseEntity<Page<InventoryItemListDTO>> listInventoryItemsByLocation(@PathVariable LocalDate localDate, @PathVariable Long locationId,
            @PageableDefault(page = 0, size = 10, sort = "expireDate", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.listInventoryItemsByLocation(localDate, locationId, pageable));
    }
    @GetMapping("/{localDate}/expired")
    public ResponseEntity<Page<InventoryItemListDTO>> listExpiredInventoryItems(@PathVariable LocalDate localDate,
            @PageableDefault(page = 0, size = 10, sort = "expireDate", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.listExpiredInventoryItems(localDate, pageable));
    }

    @GetMapping("/{localDate}/almost/{days}")
    public ResponseEntity<Page<InventoryItemListDTO>> listExpiringSoonInventoryItems(@PathVariable LocalDate localDate,
            @PathVariable int days, @PageableDefault(page = 0, size = 10, sort = "expireDate", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.listExpiringSoonInventoryItems(localDate, days, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<InventoryItemListDTO> getInventoryItemById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItemService.getInventoryItemById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id){
        inventoryItemService.deleteInventoryItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
