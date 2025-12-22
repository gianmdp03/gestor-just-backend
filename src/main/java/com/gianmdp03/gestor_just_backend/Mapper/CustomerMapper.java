package com.gianmdp03.gestor_just_backend.mapper;


import com.gianmdp03.gestor_just_backend.dto.customer.CustomerDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerRequestDTO;
import com.gianmdp03.gestor_just_backend.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Customer toEntity(CustomerRequestDTO dto);
    CustomerDetailDTO toDetailDto(Customer entity);
    CustomerListDTO toListDto(Customer entity);
}
