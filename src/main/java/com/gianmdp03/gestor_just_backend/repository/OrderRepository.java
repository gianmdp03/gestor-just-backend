package com.gianmdp03.gestor_just_backend.repository;

import com.gianmdp03.gestor_just_backend.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
