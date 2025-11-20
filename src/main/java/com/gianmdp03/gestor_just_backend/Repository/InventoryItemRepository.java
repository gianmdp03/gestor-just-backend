package com.gianmdp03.gestor_just_backend.Repository;

import com.gianmdp03.gestor_just_backend.Model.InventoryItem;
import com.gianmdp03.gestor_just_backend.Model.Product;
import com.gianmdp03.gestor_just_backend.Model.Ubication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Page<InventoryItem> findAllByProduct(Product product, Pageable pageable);
    Page<InventoryItem> findAllByUbication(Ubication ubication, Pageable pageable);
    Page<InventoryItem> findByExpireDateBefore(LocalDate date, Pageable pageable);
    Page<InventoryItem> findByExpireDateGreaterThanEqual(LocalDate date, Pageable pageable);
}
