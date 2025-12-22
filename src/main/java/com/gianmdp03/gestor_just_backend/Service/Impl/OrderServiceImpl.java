package com.gianmdp03.gestor_just_backend.service.impl;

import com.gianmdp03.gestor_just_backend.dto.order.OrderDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.dto.order.OrderRequestDTO;
import com.gianmdp03.gestor_just_backend.exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.mapper.OrderMapper;
import com.gianmdp03.gestor_just_backend.model.Customer;
import com.gianmdp03.gestor_just_backend.model.Order;
import com.gianmdp03.gestor_just_backend.model.Product;
import com.gianmdp03.gestor_just_backend.repository.CustomerRepository;
import com.gianmdp03.gestor_just_backend.repository.OrderRepository;
import com.gianmdp03.gestor_just_backend.repository.ProductRepository;
import com.gianmdp03.gestor_just_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public OrderDetailDTO addOrder(OrderRequestDTO orderRequestDTO) {
        Customer customer = customerRepository.findById(orderRequestDTO.getCustomerId()).orElseThrow
                (() -> new NotFoundException("Customer ID does not exist"));
        List<Product> products = productRepository.findAllById(orderRequestDTO.getProductIds());
        if (products.size() != orderRequestDTO.getProductIds().size()) {
            throw new NotFoundException("One or more product IDs are invalid");
        }
        Order order = orderMapper.toEntity(orderRequestDTO);
        order.setCustomer(customer);
        order.setProducts(products);
        order = orderRepository.save(order);
        return orderMapper.toDetailDto(order);
    }

    @Override
    public Page<OrderListDTO> listOrders(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(orderMapper::toListDto);
    }

    @Override
    public Page<OrderListDTO> listOrdersBetween(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Page<Order> page = orderRepository.findAllBySaleDateBetween(startDate, endDate, pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(orderMapper::toListDto);
    }

    @Override
    public Page<OrderListDTO> listOrdersByProduct(Long productId, Pageable pageable)
    {
        Page<Order> page = orderRepository.findAllByProductId(productId, pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(orderMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.delete(orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order ID does not exist")));
    }
}
