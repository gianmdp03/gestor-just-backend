package com.gianmdp03.gestor_just_backend.service.impl;

import com.gianmdp03.gestor_just_backend.dto.product.ProductDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductListDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductUpdateDTO;
import com.gianmdp03.gestor_just_backend.exception.ConflictException;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDetailDTO addProduct(ProductRequestDTO productRequestDTO) {
        if(productRepository.existsByNameAndEnabledTrue(productRequestDTO.name())){
            throw new ConflictException("Name already exists");
        }
        Optional<Product> optionalProduct = productRepository.findByName(productRequestDTO.name());
        if(optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            existingProduct.setEnabled(true);
            existingProduct = productRepository.save(existingProduct);
            return productMapper.toDetailDto(existingProduct);
        }
        Product product = productRepository.save(productMapper.toEntity(productRequestDTO));
        return productMapper.toDetailDto(product);
    }

    @Override
    @Transactional
    public ProductDetailDTO updateProduct(Long id, ProductUpdateDTO dto) {
        Product product = productRepository.findByIdAndEnabledTrue(id)
                .orElseThrow(()-> new NotFoundException("Product ID does not exist"));
        productMapper.updateEntityFromDto(dto, product);
        product = productRepository.save(product);
        return productMapper.toDetailDto(product);
    }

    @Override
    public Page<ProductListDTO> searchByName(String name, Pageable pageable) {
        Page<Product> page = productRepository.findAllByNameContainingIgnoreCaseAndEnabledTrue(name, pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(productMapper::toListDto);
    }

    @Override
    public Page<ProductListDTO> listProducts(Pageable pageable) {
        Page<Product> page = productRepository.findAllByEnabledTrue(pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(productMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findByIdAndEnabledTrue(id).orElseThrow(()-> new NotFoundException("Product ID does not exist"));
        product.setEnabled(false);
        productRepository.save(product);
    }
}
