package com.gianmdp03.gestor_just_backend.controller;

import com.gianmdp03.gestor_just_backend.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders/items")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
}
