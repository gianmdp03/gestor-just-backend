package com.gianmdp03.gestor_just_backend.Mapper;

import com.gianmdp03.gestor_just_backend.DTO.Product.ProductDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Product.ProductRequestDTO;
import com.gianmdp03.gestor_just_backend.Model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Product toEntity(ProductRequestDTO dto);
    ProductDetailDTO toDetailDto(Product entity);
    ProductListDTO toListDto(Product entity);
}
