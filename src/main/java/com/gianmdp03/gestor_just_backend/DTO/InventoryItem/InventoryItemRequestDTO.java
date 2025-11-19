package com.gianmdp03.gestor_just_backend.DTO.InventoryItem;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemRequestDTO {
    @NotNull
    private Long productId;

    @NotNull
    private Long ubicationId;

    @NotNull
    private int stock;

    @NotNull
    @FutureOrPresent
    private LocalDate expireDate;
}
