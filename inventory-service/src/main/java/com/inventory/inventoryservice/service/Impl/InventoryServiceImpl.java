package com.inventory.inventoryservice.service.Impl;

import com.inventory.inventoryservice.entity.Inventory;
import com.inventory.inventoryservice.repository.InventoryRepository;
import com.inventory.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory add(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode)
                .map(inventory -> inventory.getQuantity() > inventory.getReorderLevel())
                .orElse(false);
    }
}
