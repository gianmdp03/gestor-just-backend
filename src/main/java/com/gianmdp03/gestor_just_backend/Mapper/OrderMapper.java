package com.gianmdp03.gestor_just_backend.mapper;

import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    @Autowired
    @Lazy
    private CustomerMapper customerMapper;

    @Autowired
    @Lazy
    private OrderItemMapper orderItemMapper;

    public abstract OrderListDTO toListDto(Order entity);
}
