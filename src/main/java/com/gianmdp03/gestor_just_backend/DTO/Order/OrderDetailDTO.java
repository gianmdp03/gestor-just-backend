package com.gianmdp03.gestor_just_backend.DTO.Order;

import com.gianmdp03.gestor_just_backend.DTO.Product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.Model.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderDetailDTO(Long id, BigDecimal amount, String description, LocalDate saleDate, Customer customer, List<ProductListDTO> products) {
}
