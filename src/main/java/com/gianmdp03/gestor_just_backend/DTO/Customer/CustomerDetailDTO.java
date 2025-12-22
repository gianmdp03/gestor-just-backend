package com.gianmdp03.gestor_just_backend.dto.customer;

import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;

import java.util.List;

public record CustomerDetailDTO(Long id, String name, String lastname, String phoneNumber, List<OrderListDTO> orders) {
}
