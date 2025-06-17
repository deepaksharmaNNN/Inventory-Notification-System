package com.inventory.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String skuCode;

    Integer quantity;

    Integer reorderLevel;
}
