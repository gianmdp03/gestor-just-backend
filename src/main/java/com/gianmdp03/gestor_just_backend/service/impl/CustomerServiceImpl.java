package com.gianmdp03.gestor_just_backend.service.impl;

import com.gianmdp03.gestor_just_backend.dto.customer.CustomerDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerUpdateDTO;
import com.gianmdp03.gestor_just_backend.exception.ConflictException;
import com.gianmdp03.gestor_just_backend.exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.mapper.CustomerMapper;
import com.gianmdp03.gestor_just_backend.model.Customer;
import com.gianmdp03.gestor_just_backend.repository.CustomerRepository;
import com.gianmdp03.gestor_just_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerDetailDTO addCustomer(CustomerRequestDTO customerRequestDTO) {
        if(customerRepository.existsByPhoneNumber(customerRequestDTO.phoneNumber()))
            throw new ConflictException("Phone number already exists");
        Customer customer = customerRepository.save(customerMapper.toEntity(customerRequestDTO));
        return customerMapper.toDetailDto(customer);
    }

    @Override
    @Transactional
    public CustomerDetailDTO updateCustomer(Long id, CustomerUpdateDTO dto){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new NotFoundException("Customer ID does not exist"));
        customerMapper.updateEntityFromDto(dto, customer);
        customer = customerRepository.save(customer);
        return customerMapper.toDetailDto(customer);
    }

    @Override
    public Page<CustomerListDTO> searchCustomersByFullname(String fullname, Pageable pageable){
        Page<Customer> page = customerRepository.findByFullnameContainingIgnoreCase(fullname, pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(customerMapper::toListDto);
    }

    @Override
    public Page<CustomerListDTO> listCustomers(Pageable pageable) {
        Page<Customer> list = customerRepository.findAll(pageable);
        if (list.isEmpty())
            throw new NotFoundException("List is empty");
        return list.map(customerMapper::toListDto);
    }

    @Override
    public CustomerDetailDTO getCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new NotFoundException("Customer ID does not exist"));
        return customerMapper.toDetailDto(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Customer ID does not exist"));
        customerRepository.delete(customer);
    }
}
