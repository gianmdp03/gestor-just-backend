package com.gianmdp03.gestor_just_backend.Service.Impl;

import com.gianmdp03.gestor_just_backend.DTO.Location.LocationDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Location.LocationRequestDTO;
import com.gianmdp03.gestor_just_backend.Exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.Mapper.LocationMapper;
import com.gianmdp03.gestor_just_backend.Model.Location;
import com.gianmdp03.gestor_just_backend.Repository.LocationRepository;
import com.gianmdp03.gestor_just_backend.Service.LocationService;
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
