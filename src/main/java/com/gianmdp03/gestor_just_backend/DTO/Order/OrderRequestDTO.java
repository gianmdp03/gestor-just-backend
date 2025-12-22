package com.gianmdp03.gestor_just_backend.dto.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {
    @NotNull
    private BigDecimal amount;

    private String description;

    @NotNull
    @PastOrPresent
    private LocalDate saleDate;

    @NotNull
    private Long customerId;

    @NotNull
    @NotEmpty
    private List<Long> productIds;
}
