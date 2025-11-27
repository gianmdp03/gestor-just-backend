package com.gianmdp03.gestor_just_backend.Service;

import com.gianmdp03.gestor_just_backend.DTO.Order.OrderDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Order.OrderRequestDTO;
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
