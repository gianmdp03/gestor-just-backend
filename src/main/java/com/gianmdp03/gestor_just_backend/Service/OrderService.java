package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.order.OrderDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.dto.order.OrderRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OrderService {
    OrderDetailDTO addOrder(OrderRequestDTO orderRequestDTO);
    Page<OrderListDTO> listOrders(Pageable pageable);
    Page<OrderListDTO> listOrdersBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<OrderListDTO> listOrdersByProduct(Long productId, Pageable pageable);
    void deleteOrder(Long id);
}
