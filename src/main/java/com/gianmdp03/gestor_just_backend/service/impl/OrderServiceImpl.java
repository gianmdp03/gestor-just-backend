package com.gianmdp03.gestor_just_backend.service.impl;

import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemRequestDTO;
import com.gianmdp03.gestor_just_backend.exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.mapper.OrderItemMapper;
import com.gianmdp03.gestor_just_backend.mapper.OrderMapper;
import com.gianmdp03.gestor_just_backend.model.Customer;
import com.gianmdp03.gestor_just_backend.model.Order;
import com.gianmdp03.gestor_just_backend.model.OrderItem;
import com.gianmdp03.gestor_just_backend.model.Product;
import com.gianmdp03.gestor_just_backend.repository.CustomerRepository;
import com.gianmdp03.gestor_just_backend.repository.OrderItemRepository;
import com.gianmdp03.gestor_just_backend.repository.OrderRepository;
import com.gianmdp03.gestor_just_backend.repository.ProductRepository;
import com.gianmdp03.gestor_just_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Order addOrder(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow
                (() -> new NotFoundException("Customer ID does not exist"));
        Order order = new Order(LocalDateTime.now(), customer);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public OrderListDTO addOrderWithItems(List<OrderItemRequestDTO> orderItemsDTO, Long customerId){
        if(orderItemsDTO.isEmpty()){
            throw new NotFoundException("OrderItem list is empty");
        }
        Order order = addOrder(customerId);
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequestDTO dto : orderItemsDTO) {
            OrderItem item = orderItemMapper.toEntity(dto);
            item.setOrder(order);
            Product product = productRepository.findById(dto.productId())
                    .orElseThrow(() -> new NotFoundException("Product ID " + dto.productId() + " does not exist"));
            item.setProduct(product);

            orderItems.add(item);
        }
        orderItemRepository.saveAll(orderItems);
        order = orderRepository.findById(order.getId())
                .orElseThrow(()-> new NotFoundException("Order ID does not exist"));

        return orderMapper.toListDto(order);
    }

    @Override
    public Page<OrderItemListDTO> listItemsByOrder(Long orderId, Pageable pageable) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("Order ID does not exist"));
        Page<OrderItem> page = orderItemRepository.findAllByOrder(order, pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(orderItemMapper::toListDto);
    }

    @Override
    public Page<OrderListDTO> listOrders(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        if(page.isEmpty())
            return Page.empty();
        return page.map(orderMapper::toListDto);
    }

    @Override
    public Page<OrderListDTO> listOrdersBetween(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);
        Page<Order> page = orderRepository.findAllBySaleDateBetween(start, end, pageable);
        if(page.isEmpty())
            return Page.empty();
        return page.map(orderMapper::toListDto);
    }

    @Override
    public OrderListDTO getOrderById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(()-> new NotFoundException("Order ID does not exist"));
        return orderMapper.toListDto(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.delete(orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order ID does not exist")));
    }

    @Override
    @Transactional
    public void deleteItem(Long id){
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new NotFoundException("Order item ID does not exist"));
        orderItemRepository.delete(orderItem);
    }
}
