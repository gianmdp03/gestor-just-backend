package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.product.ProductDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDetailDTO addProduct(ProductRequestDTO productRequestDTO);
    Page<ProductListDTO> listProducts(Pageable pageable);
    void deleteProduct(Long id);
}
