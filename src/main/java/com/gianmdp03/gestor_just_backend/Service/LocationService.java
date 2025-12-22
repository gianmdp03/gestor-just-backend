package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.location.LocationDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
    LocationDetailDTO addLocation(LocationRequestDTO locationRequestDTO);
    Page<LocationListDTO> listLocations(Pageable pageable);
    void deleteLocation(Long id);
}
