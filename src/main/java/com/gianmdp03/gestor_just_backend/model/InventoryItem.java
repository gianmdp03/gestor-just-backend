package com.gianmdp03.gestor_just_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "inventory_items")
@Getter
@Setter
@NoArgsConstructor
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private LocalDate expireDate;

    public InventoryItem(Product product, Location location, int stock, LocalDate expireDate) {
        this.product = product;
        this.location = location;
        this.stock = stock;
        this.expireDate = expireDate;
    }
}
