package com.gianmdp03.gestor_just_backend.Service;

import com.gianmdp03.gestor_just_backend.DTO.Ubication.UbicationDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Ubication.UbicationListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Ubication.UbicationRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UbicationService {
    UbicationDetailDTO addUbication(UbicationRequestDTO ubicationRequestDTO);
    Page<UbicationListDTO> listUbications(Pageable pageable);
    void deleteUbication(Long id);
}
