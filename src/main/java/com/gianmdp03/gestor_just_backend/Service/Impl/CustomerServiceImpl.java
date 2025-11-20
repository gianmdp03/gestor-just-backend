package com.gianmdp03.gestor_just_backend.Service.Impl;

import com.gianmdp03.gestor_just_backend.DTO.Customer.CustomerDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Customer.CustomerRequestDTO;
import com.gianmdp03.gestor_just_backend.Exception.ConflictException;
import com.gianmdp03.gestor_just_backend.Exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.Mapper.CustomerMapper;
import com.gianmdp03.gestor_just_backend.Model.Customer;
import com.gianmdp03.gestor_just_backend.Repository.CustomerRepository;
import com.gianmdp03.gestor_just_backend.Service.CustomerService;
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
        if(customerRepository.existsByPhoneNumber(customerRequestDTO.getPhoneNumber())){
            throw new ConflictException("Phone number already exists");
        }
        Customer customer = customerRepository.save(customerMapper.toEntity(customerRequestDTO));
        return customerMapper.toDetailDto(customer);
    }

    @Override
    public Page<CustomerListDTO> listCustomers(Pageable pageable) {
        Page<Customer> list = customerRepository.findAll(pageable);
        if(list.isEmpty()){
            throw new NotFoundException("List is empty");
        }
        return list.map(customerMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.delete(customerRepository.findById(id).orElseThrow(() -> new NotFoundException("ID does not exist")));
    }
}
