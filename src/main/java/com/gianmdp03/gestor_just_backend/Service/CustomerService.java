package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.customer.CustomerDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerDetailDTO addCustomer(CustomerRequestDTO customerRequestDTO);
    Page<CustomerListDTO> listCustomers(Pageable pageable);
    void deleteCustomer(Long id);
}
