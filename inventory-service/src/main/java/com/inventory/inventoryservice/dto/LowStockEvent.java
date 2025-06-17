package com.inventory.inventoryservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LowStockEvent {
    String skuCode;
    int quantity;
    int reorderLevel;
}
