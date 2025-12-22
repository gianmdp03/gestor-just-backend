package com.gianmdp03.gestor_just_backend.dto.order;

import com.gianmdp03.gestor_just_backend.model.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderListDTO(Long id, BigDecimal amount, LocalDate saleDate, Customer customer) {
}
