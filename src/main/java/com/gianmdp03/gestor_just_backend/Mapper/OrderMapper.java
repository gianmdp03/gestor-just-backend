package com.gianmdp03.gestor_just_backend.Mapper;

import com.gianmdp03.gestor_just_backend.DTO.Order.OrderDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Order.OrderRequestDTO;
import com.gianmdp03.gestor_just_backend.Model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductMapper.class})
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "products", ignore = true)
    Order toEntity(OrderRequestDTO dto);
    OrderDetailDTO toDetailDto(Order entity);
    OrderListDTO toListDto(Order entity);
}
