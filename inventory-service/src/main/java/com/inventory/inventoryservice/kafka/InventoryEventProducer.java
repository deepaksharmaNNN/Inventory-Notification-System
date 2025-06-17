package com.inventory.inventoryservice.kafka;

import com.inventory.inventoryservice.dto.LowStockEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryEventProducer {

    private final KafkaTemplate<String, LowStockEvent> kafkaTemplate;

    public void sendLowStockEvent(LowStockEvent lowStockEvent) {
        kafkaTemplate.send("low-stock-alert", lowStockEvent);
    }
}
