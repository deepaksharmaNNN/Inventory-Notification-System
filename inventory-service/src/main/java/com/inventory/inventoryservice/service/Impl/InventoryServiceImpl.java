package com.inventory.inventoryservice.service.Impl;

import com.inventory.inventoryservice.dto.LowStockEvent;
import com.inventory.inventoryservice.entity.Inventory;
import com.inventory.inventoryservice.kafka.InventoryEventProducer;
import com.inventory.inventoryservice.repository.InventoryRepository;
import com.inventory.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryEventProducer inventoryEventProducer;

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory add(Inventory inventory) {
        Inventory savedInventory = inventoryRepository.save(inventory);
        if(savedInventory.getQuantity() <= savedInventory.getReorderLevel()) {
            inventoryEventProducer.sendLowStockEvent(
                    LowStockEvent.builder()
                            .skuCode(savedInventory.getSkuCode())
                            .quantity(savedInventory.getQuantity())
                            .reorderLevel(savedInventory.getReorderLevel())
                            .build()
            );
        }
        return savedInventory;
    }

    @Override
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode)
                .map(inventory -> inventory.getQuantity() > inventory.getReorderLevel())
                .orElse(false);
    }
}
