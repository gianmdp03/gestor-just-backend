package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.customer.CustomerDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerDetailDTO addCustomer(CustomerRequestDTO customerRequestDTO);
    CustomerDetailDTO updateCustomer(Long id, CustomerUpdateDTO dto);
    Page<CustomerListDTO> searchCustomersByFullname(String fullname, Pageable pageable);
    Page<CustomerListDTO> listCustomers(Pageable pageable);
    CustomerDetailDTO getCustomerById(Long id);
    void deleteCustomer(Long id);
}
