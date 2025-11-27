package com.gianmdp03.gestor_just_backend.Repository;

import com.gianmdp03.gestor_just_backend.Model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllBySaleDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Order> findAllByProductId(Long id, Pageable pageable);
}
