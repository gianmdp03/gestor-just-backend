package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemRequestDTO;
import com.gianmdp03.gestor_just_backend.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order addOrder(Long customerId);
    OrderListDTO addItems(List<OrderItemRequestDTO> orderItems, Long customerId);
    Page<OrderListDTO> listOrders(Pageable pageable);
    Page<OrderListDTO> listOrdersBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    void deleteOrder(Long id);
    void deleteItem(Long id);
}
