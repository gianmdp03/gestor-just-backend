package com.gianmdp03.gestor_just_backend.dto.orderitem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequestDTO(@NotNull Long productId,
                                  @NotNull @Positive int amount) {
}
