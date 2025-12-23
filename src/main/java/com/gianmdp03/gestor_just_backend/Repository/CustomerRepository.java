package com.gianmdp03.gestor_just_backend.repository;

import com.gianmdp03.gestor_just_backend.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Page<Customer> findByFullnameContainingIgnoreCase(String fullname, Pageable pageable);
}
