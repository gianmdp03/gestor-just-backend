package com.gianmdp03.gestor_just_backend.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Product(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
