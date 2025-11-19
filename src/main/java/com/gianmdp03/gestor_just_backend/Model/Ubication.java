package com.gianmdp03.gestor_just_backend.Model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ubications")
@Getter
@Setter
@NoArgsConstructor
public class Ubication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "ubication")
    private List<InventoryItem> inventoryItem = new ArrayList<>();

    @Builder
    public Ubication(String name) {
        this.name = name;
    }
}
