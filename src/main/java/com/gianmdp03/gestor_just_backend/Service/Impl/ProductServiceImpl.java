package com.gianmdp03.gestor_just_backend.Service.Impl;

import com.gianmdp03.gestor_just_backend.DTO.Order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Product.ProductDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Product.ProductRequestDTO;
import com.gianmdp03.gestor_just_backend.Exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.Mapper.ProductMapper;
import com.gianmdp03.gestor_just_backend.Model.Order;
import com.gianmdp03.gestor_just_backend.Model.Product;
import com.gianmdp03.gestor_just_backend.Repository.ProductRepository;
import com.gianmdp03.gestor_just_backend.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
