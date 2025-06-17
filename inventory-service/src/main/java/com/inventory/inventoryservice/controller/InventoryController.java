package com.inventory.inventoryservice.controller;

import com.inventory.inventoryservice.entity.Inventory;
import com.inventory.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAll() {
        return inventoryService.getAll();
    }

    @PostMapping
    public Inventory add(@RequestBody Inventory inventory) {
        return inventoryService.add(inventory);
    }

    @GetMapping("{skuCode}")
    public boolean isInStock(@PathVariable String skuCode) {
        return inventoryService.isInStock(skuCode);
    }

}
