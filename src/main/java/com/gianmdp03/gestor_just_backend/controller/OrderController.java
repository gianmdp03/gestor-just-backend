package com.gianmdp03.gestor_just_backend.controller;

import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemRequestDTO;
import com.gianmdp03.gestor_just_backend.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{localDateTime}/{customerId}")
    public ResponseEntity<OrderListDTO> addOrderWithItems(@PathVariable LocalDateTime localDateTime, @PathVariable Long customerId,
                                                          @Valid @RequestBody List<OrderItemRequestDTO> orderItems)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrderWithItems(localDateTime, orderItems, customerId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Page<OrderItemListDTO>> listItemsByOrder(
            @PathVariable Long orderId,
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable)
        {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.listItemsByOrder(orderId, pageable));
        }

    @GetMapping
    public ResponseEntity<Page<OrderListDTO>> listOrders(
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listOrders(pageable));
    }

    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity<Page<OrderListDTO>> listOrdersBetween(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate,
            @PageableDefault(page = 0, size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listOrdersBetween(startDate, endDate, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OrderListDTO> getOrderById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        orderService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
