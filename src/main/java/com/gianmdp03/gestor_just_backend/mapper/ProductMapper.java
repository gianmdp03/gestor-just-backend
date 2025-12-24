package com.gianmdp03.gestor_just_backend.mapper;

import com.gianmdp03.gestor_just_backend.dto.product.ProductDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductUpdateDTO;
import com.gianmdp03.gestor_just_backend.model.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Mapping(target = "id", ignore = true)
    public abstract Product toEntity(ProductRequestDTO dto);
    public abstract ProductDetailDTO toDetailDto(Product entity);
    public abstract ProductListDTO toListDto(Product entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    public abstract void updateEntityFromDto(ProductUpdateDTO dto, @MappingTarget Product entity);
}
