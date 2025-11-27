package com.gianmdp03.gestor_just_backend.Mapper;


import com.gianmdp03.gestor_just_backend.DTO.Customer.CustomerDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Customer.CustomerRequestDTO;
import com.gianmdp03.gestor_just_backend.Model.Customer;
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
