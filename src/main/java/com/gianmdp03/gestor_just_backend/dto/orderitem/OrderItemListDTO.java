package com.gianmdp03.gestor_just_backend.dto.orderitem;

import com.gianmdp03.gestor_just_backend.dto.product.ProductListDTO;

public record OrderItemListDTO(ProductListDTO product, int amount) {
}
