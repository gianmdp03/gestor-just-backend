package com.gianmdp03.gestor_just_backend.service.impl;

import com.gianmdp03.gestor_just_backend.dto.product.ProductDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductRequestDTO;
import com.gianmdp03.gestor_just_backend.exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.mapper.ProductMapper;
import com.gianmdp03.gestor_just_backend.model.Product;
import com.gianmdp03.gestor_just_backend.repository.ProductRepository;
import com.gianmdp03.gestor_just_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDetailDTO addProduct(ProductRequestDTO productRequestDTO) {
        Product product = productRepository.save(productMapper.toEntity(productRequestDTO));
        return productMapper.toDetailDto(product);
    }

    @Override
    public Page<ProductListDTO> listProducts(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(productMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.delete(productRepository.findById(id).orElseThrow(()-> new NotFoundException("Product ID does not exist")));
    }
}
