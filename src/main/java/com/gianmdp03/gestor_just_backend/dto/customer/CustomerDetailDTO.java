package com.gianmdp03.gestor_just_backend.dto.customer;

import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;

import java.util.List;

public record CustomerDetailDTO(Long id, String fullname, String phoneNumber, List<OrderListDTO> orders) {
}
