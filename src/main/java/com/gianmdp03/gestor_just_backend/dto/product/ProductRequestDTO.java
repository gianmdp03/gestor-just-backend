package com.gianmdp03.gestor_just_backend.dto.product;

import jakarta.validation.constraints.NotBlank;

public record ProductRequestDTO (@NotBlank String name,
                                 String imageUrl){}
