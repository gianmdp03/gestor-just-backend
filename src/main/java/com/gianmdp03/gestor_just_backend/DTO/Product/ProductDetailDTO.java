package com.gianmdp03.gestor_just_backend.dto.product;

import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;

import java.util.List;

public record ProductDetailDTO(Long id, String name, String imageUrl, List<OrderListDTO> orders) {
}
