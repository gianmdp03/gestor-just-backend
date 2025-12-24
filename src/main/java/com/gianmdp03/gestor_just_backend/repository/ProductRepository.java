package com.gianmdp03.gestor_just_backend.repository;

import com.gianmdp03.gestor_just_backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByEnabledTrue(Pageable pageable);
    boolean existsByNameAndEnabledTrue(String name);
    Optional<Product> findByName(String name);
    Optional<Product> findByIdAndEnabledTrue(Long id);
    Page<Product> findAllByNameContainingIgnoreCaseAndEnabledTrue(String name, Pageable pageable);
}
