package com.gianmdp03.gestor_just_backend.Model;

import jakarta.persistence.*;
import lombok.Builder;
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
    @JoinColumn(name = "ubication_id")
    private Ubication ubication;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private LocalDate expireDate;

    @Builder
    public InventoryItem(Product product, Ubication ubication, int stock, LocalDate expireDate) {
        this.product = product;
        this.ubication = ubication;
        this.stock = stock;
        this.expireDate = expireDate;
    }
}
