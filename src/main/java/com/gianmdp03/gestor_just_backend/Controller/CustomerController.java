package com.gianmdp03.gestor_just_backend.controller;

import com.gianmdp03.gestor_just_backend.dto.customer.CustomerDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.customer.CustomerUpdateDTO;
import com.gianmdp03.gestor_just_backend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDetailDTO> addCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDetailDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, dto));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerListDTO>> listCustomers(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.listCustomers(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
