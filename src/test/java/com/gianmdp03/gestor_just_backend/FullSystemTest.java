package com.gianmdp03.gestor_just_backend;

import com.gianmdp03.gestor_just_backend.dto.customer.CustomerRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.inventoryitem.InventoryItemRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.location.LocationRequestDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductRequestDTO;
import com.gianmdp03.gestor_just_backend.exception.ConflictException;
import com.gianmdp03.gestor_just_backend.model.*;
import com.gianmdp03.gestor_just_backend.repository.*;
import com.gianmdp03.gestor_just_backend.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class FullSystemTest {

    @Autowired private CustomerService customerService;
    @Autowired private CustomerRepository customerRepository;

    @Autowired private ProductService productService;
    @Autowired private ProductRepository productRepository;

    @Autowired private LocationService locationService;
    @Autowired private LocationRepository locationRepository;

    @Autowired private InventoryItemService inventoryItemService;
    @Autowired private InventoryItemRepository inventoryItemRepository;

    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    @Test
    @DisplayName("1. CLIENTES: Valida creación y bloqueo de duplicados (Usando Fullname)")
    void testCicloDeVidaCliente() {
        // A. Crear Cliente (CORREGIDO: Solo 2 argumentos ahora)
        CustomerRequestDTO dto = new CustomerRequestDTO("Juan Perez", "11223344");
        var cliente = customerService.addCustomer(dto);

        assertThat(cliente.id()).isNotNull();
        // Validamos fullname directamente
        assertThat(cliente.fullname()).isEqualTo("Juan Perez");

        assertThat(customerRepository.existsByPhoneNumber("11223344")).isTrue();

        // B. Intentar duplicar teléfono
        CustomerRequestDTO duplicado = new CustomerRequestDTO("Otro Gomez", "11223344");

        assertThatThrownBy(() -> customerService.addCustomer(duplicado))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    @DisplayName("2. PRODUCTOS: Valida Borrado Lógico (Soft Delete)")
    void testCicloDeVidaProducto() {
        var prod = productService.addProduct(new ProductRequestDTO("Eucasol", "img.jpg"));
        Long id = prod.id();

        productService.deleteProduct(id);

        // Verificamos que enabled = false
        Product enDb = productRepository.findById(id).orElseThrow();
        assertThat(enDb.isEnabled()).isFalse();
    }

    @Test
    @DisplayName("3. INVENTARIO: Protege Ubicación si tiene Items")
    void testIntegridadInventario() {
        var loc = locationService.addLocation(new LocationRequestDTO("Estante A"));
        var prod = productRepository.save(new Product("Oleo 31", "oleo.jpg"));

        var item = inventoryItemService.addInventoryItem(new InventoryItemRequestDTO(
                prod.getId(), loc.id(), 50, LocalDate.now().plusYears(1)));

        // Intentar borrar ubicación ocupada (Tu corrección en LocationService hará que esto funcione)
        assertThatThrownBy(() -> locationService.deleteLocation(loc.id()))
                .isInstanceOf(ConflictException.class);

        // Vaciar y borrar
        inventoryItemService.deleteInventoryItem(item.id());
        locationService.deleteLocation(loc.id());
        assertThat(locationRepository.existsById(loc.id())).isFalse();
    }

    @Test
    @DisplayName("4. ORDENES: Prueba Cascada y manipulación de items")
    void testCicloCompletoOrdenes() {
        // --- Setup (CORREGIDO: Constructor de Customer con fullname) ---
        Customer c = customerRepository.save(new Customer("Maria Gomez", "99887766"));
        Product p = productRepository.save(new Product("Crema Tomillo", "tom.jpg"));

        // --- Caso A: Borrar Orden borra Items ---
        Order orden = new Order(LocalDateTime.now(), c);
        OrderItem item1 = new OrderItem(p, 2);
        item1.setOrder(orden);

        if (orden.getOrderItems() == null) orden.setOrderItems(new ArrayList<>());
        orden.getOrderItems().add(item1);

        orden = orderRepository.save(orden);
        Long ordenId = orden.getId();
        Long itemId = orden.getOrderItems().get(0).getId();

        orderRepository.deleteById(ordenId);
        orderRepository.flush();

        assertThat(orderRepository.existsById(ordenId)).isFalse();
        assertThat(orderItemRepository.existsById(itemId)).isFalse();

        // --- Caso B: Borrar Item respeta Orden ---
        Order orden2 = orderRepository.save(new Order(LocalDateTime.now(), c));
        OrderItem item2 = new OrderItem(p, 5);
        item2.setOrder(orden2);
        item2 = orderItemRepository.save(item2);

        orderItemRepository.deleteById(item2.getId());
        orderItemRepository.flush();

        assertThat(orderItemRepository.existsById(item2.getId())).isFalse();
        assertThat(orderRepository.existsById(orden2.getId())).isTrue();
    }
}