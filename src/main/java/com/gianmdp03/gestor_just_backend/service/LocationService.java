package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.location.LocationDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
    LocationDetailDTO addLocation(LocationRequestDTO locationRequestDTO);
    LocationDetailDTO updateLocation(Long id, LocationUpdateDTO dto);
    Page<LocationListDTO> searchByName(String name, Pageable pageable);
    Page<LocationListDTO> listLocations(Pageable pageable);
    void deleteLocation(Long id);
}
