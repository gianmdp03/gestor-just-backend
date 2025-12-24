package com.gianmdp03.gestor_just_backend.controller;

import com.gianmdp03.gestor_just_backend.dto.product.ProductDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductUpdateDTO;
import com.gianmdp03.gestor_just_backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDetailDTO> addProduct(@Valid @RequestBody ProductRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, dto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Page<ProductListDTO>> searchByName(
            @PathVariable String name,
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchByName(name, pageable));
    }

    @GetMapping
    public ResponseEntity<Page<ProductListDTO>> listProducts(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productService.listProducts(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
