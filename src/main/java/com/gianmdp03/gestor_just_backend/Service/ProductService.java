package com.gianmdp03.gestor_just_backend.Service;

import com.gianmdp03.gestor_just_backend.DTO.Order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Product.ProductDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Product.ProductRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDetailDTO addProduct(ProductRequestDTO productRequestDTO);
    Page<ProductListDTO> listProducts(Pageable pageable);
    void deleteProduct(Long id);
}
