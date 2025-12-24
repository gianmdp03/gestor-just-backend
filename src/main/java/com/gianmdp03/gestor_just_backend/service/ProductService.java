package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.product.ProductDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDetailDTO addProduct(ProductRequestDTO productRequestDTO);
    ProductDetailDTO updateProduct(Long id, ProductUpdateDTO dto);
    Page<ProductListDTO> searchByName(String name, Pageable pageable);
    Page<ProductListDTO> listProducts(Pageable pageable);
    void deleteProduct(Long id);
}
