package com.gianmdp03.gestor_just_backend.service.impl;

import com.gianmdp03.gestor_just_backend.dto.location.LocationDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationRequestDTO;
import com.gianmdp03.gestor_just_backend.exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.mapper.LocationMapper;
import com.gianmdp03.gestor_just_backend.model.Location;
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

    @Override
    @Transactional
    public LocationDetailDTO addLocation(LocationRequestDTO locationRequestDTO) {
        Location location = locationRepository.save(locationMapper.toEntity(locationRequestDTO));
        return locationMapper.toDetailDto(location);
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
        locationRepository.delete(locationRepository.findById(id).orElseThrow
                (() -> new NotFoundException("Location ID does not exist")));
    }
}
