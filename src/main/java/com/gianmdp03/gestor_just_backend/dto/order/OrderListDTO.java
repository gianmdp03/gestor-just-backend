package com.gianmdp03.gestor_just_backend.dto.order;

import com.gianmdp03.gestor_just_backend.dto.customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemListDTO;

import java.time.LocalDateTime;
import java.util.List;

public record OrderListDTO(Long id, LocalDateTime saleDate, List<OrderItemListDTO> orderItems, CustomerListDTO customer) {
}
