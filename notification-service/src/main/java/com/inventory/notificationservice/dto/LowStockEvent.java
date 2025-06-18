package com.inventory.notificationservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LowStockEvent {
    String skuCode;
    int quantity;
    int reorderLevel;
}
