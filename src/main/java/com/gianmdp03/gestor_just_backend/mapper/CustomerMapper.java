package com.gianmdp03.gestor_just_backend.mapper;

import com.gianmdp03.gestor_just_backend.dto.customer.CustomerDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerUpdateDTO;
import com.gianmdp03.gestor_just_backend.model.Customer;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public abstract class CustomerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    public abstract Customer toEntity(CustomerRequestDTO dto);
    public abstract CustomerDetailDTO toDetailDto(Customer entity);
    public abstract CustomerListDTO toListDto(Customer entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    public abstract void updateEntityFromDto(CustomerUpdateDTO dto, @MappingTarget Customer entity);
}
