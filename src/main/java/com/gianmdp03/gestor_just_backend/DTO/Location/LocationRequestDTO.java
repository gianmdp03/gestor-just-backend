package com.gianmdp03.gestor_just_backend.dto.location;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationRequestDTO {
    @NotBlank
    private String name;
}
