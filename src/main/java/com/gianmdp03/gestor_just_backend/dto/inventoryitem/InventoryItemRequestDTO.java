package com.gianmdp03.gestor_just_backend.dto.inventoryitem;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InventoryItemRequestDTO (@NotNull Long productId,
                                       @NotNull Long locationId,
                                       @NotNull int stock,
                                       @NotNull @FutureOrPresent LocalDate expireDate){}
