package com.inventory.notificationservice.kafka;

import com.inventory.notificationservice.dto.LowStockEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LowStockEventConsumer {

    @KafkaListener(topics = "low-stock-alert", groupId = "notification-group")
    public void handleLowStockEvent(LowStockEvent lowStockEvent) {
        log.info("Received Low Stock Event {}", lowStockEvent);

        // In the future, send email/SMS using Twilio/SendGrid
    }
}
