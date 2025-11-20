package com.gianmdp03.gestor_just_backend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Customer(String name, String lastname, String phoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }
}
