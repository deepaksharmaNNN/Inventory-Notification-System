package com.inventory.inventoryservice.service;

import com.inventory.inventoryservice.entity.Inventory;

import java.util.List;

public interface InventoryService {
    public List<Inventory> getAll();
    public Inventory add(Inventory inventory);
    public boolean isInStock(String skuCode);
}
