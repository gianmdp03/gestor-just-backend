package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemRequestDTO;

public interface OrderItemService {
    OrderItemListDTO addOrderItem(OrderItemRequestDTO dto);
    OrderItemListDTO listOrderItems(Long orderId);
}
