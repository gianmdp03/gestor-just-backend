package com.gianmdp03.gestor_just_backend.DTO.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
    @NotBlank
    private String name;

    private String imageUrl;
}
