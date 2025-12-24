package com.gianmdp03.gestor_just_backend.service.impl;

import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemUpdateDTO;
import com.gianmdp03.gestor_just_backend.exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.mapper.InventoryItemMapper;
import com.gianmdp03.gestor_just_backend.model.InventoryItem;
import com.gianmdp03.gestor_just_backend.model.Location;
import com.gianmdp03.gestor_just_backend.model.Product;
import com.gianmdp03.gestor_just_backend.repository.InventoryItemRepository;
import com.gianmdp03.gestor_just_backend.repository.LocationRepository;
import com.gianmdp03.gestor_just_backend.repository.ProductRepository;
import com.gianmdp03.gestor_just_backend.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryItemServiceImpl implements InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public InventoryItemListDTO addInventoryItem(InventoryItemRequestDTO inventoryItemRequestDTO) {
        Product product = productRepository.findById(inventoryItemRequestDTO.productId()).orElseThrow(
                () -> new NotFoundException("Product ID does not exist")
        );
        Location location = locationRepository.findById(inventoryItemRequestDTO.locationId()).orElseThrow(
                () -> new NotFoundException("Location ID does not exist")
        );

        InventoryItem inventoryItem = inventoryItemRepository.save(inventoryItemMapper.toEntity(inventoryItemRequestDTO));
        return inventoryItemMapper.toListDto(inventoryItem);
    }

    @Override
    @Transactional
    public InventoryItemListDTO updateInventoryItem(Long id, InventoryItemUpdateDTO dto){
        InventoryItem inventoryItem = inventoryItemRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("InventoryItem ID does not exist"));
        inventoryItemMapper.updateEntityFromDto(dto, inventoryItem);
        inventoryItem = inventoryItemRepository.save(inventoryItem);
        return inventoryItemMapper.toListDto(inventoryItem);
    }

    @Override
    public Page<InventoryItemListDTO> listInventoryItems(Pageable pageable) {
        Page<InventoryItem> page = inventoryItemRepository.findByExpireDateBefore(LocalDate.now(), pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(inventoryItemMapper::toListDto);
    }

    @Override
    public Page<InventoryItemListDTO> listInventoryItemsByProduct(Long productId, Pageable pageable) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product ID does not exist"));
        Page<InventoryItem> page = inventoryItemRepository.findAllByProductAndExpireDateBefore(product, LocalDate.now(), pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(inventoryItemMapper::toListDto);
    }

    @Override
    public Page<InventoryItemListDTO> listInventoryItemsByLocation(Long locationId, Pageable pageable) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new NotFoundException("Location ID does not exist"));
        Page<InventoryItem> page = inventoryItemRepository.findAllByLocationAndExpireDateBefore(location, LocalDate.now(), pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(inventoryItemMapper::toListDto);
    }

    @Override
    public Page<InventoryItemListDTO> listExpiredInventoryItems(Pageable pageable) {
        Page<InventoryItem> page = inventoryItemRepository.findByExpireDateGreaterThanEqual(LocalDate.now(), pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(inventoryItemMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteInventoryItem(Long id) {
        inventoryItemRepository.delete(inventoryItemRepository.findById(id).orElseThrow
                (() -> new NotFoundException("InventoryItem ID does not exist")));
    }
}
