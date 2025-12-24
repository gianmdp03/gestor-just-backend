package com.gianmdp03.gestor_just_backend.service.impl;

import com.gianmdp03.gestor_just_backend.dto.location.LocationDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationUpdateDTO;
import com.gianmdp03.gestor_just_backend.exception.ConflictException;
import com.gianmdp03.gestor_just_backend.exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.mapper.LocationMapper;
import com.gianmdp03.gestor_just_backend.model.Location;
import com.gianmdp03.gestor_just_backend.repository.InventoryItemRepository;
import com.gianmdp03.gestor_just_backend.repository.LocationRepository;
import com.gianmdp03.gestor_just_backend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final InventoryItemRepository inventoryItemRepository;

    @Override
    @Transactional
    public LocationDetailDTO addLocation(LocationRequestDTO locationRequestDTO) {
        Location location = locationRepository.save(locationMapper.toEntity(locationRequestDTO));
        return locationMapper.toDetailDto(location);
    }

    @Override
    @Transactional
    public LocationDetailDTO updateLocation(Long id, LocationUpdateDTO dto) {
        Location location = locationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Location ID does not exist"));
        locationMapper.updateEntityFromDto(dto, location);
        location = locationRepository.save(location);
        return locationMapper.toDetailDto(location);
    }

    @Override
    public Page<LocationListDTO> searchByName(String name, Pageable pageable) {
        Page<Location> page = locationRepository.findByNameContainingIgnoreCase(name, pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(locationMapper::toListDto);
    }

    @Override
    public Page<LocationListDTO> listLocations(Pageable pageable) {
        Page<Location> page = locationRepository.findAll(pageable);
        if(page.isEmpty())
            throw new NotFoundException("Location ID does not exist");
        return page.map(locationMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Location ID does not exist"));
        if(inventoryItemRepository.existsByLocation(location)){
            throw new ConflictException("Cannot delete, location has inventory items");
        }
        locationRepository.delete(location);
    }
}
