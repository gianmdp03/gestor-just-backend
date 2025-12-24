package com.gianmdp03.gestor_just_backend.dto.location;

import jakarta.validation.constraints.NotBlank;

public record LocationRequestDTO (@NotBlank String name){}
