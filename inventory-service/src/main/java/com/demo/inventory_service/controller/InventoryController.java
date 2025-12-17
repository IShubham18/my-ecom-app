package com.demo.inventory_service.controller;

import com.demo.inventory_service.dto.InventoryDto;
import com.demo.inventory_service.service.Inventoryservice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/inventory")
@RestController
public class InventoryController {

    private final Inventoryservice inventoryservice;

    public InventoryController(Inventoryservice inventoryservice) {
        this.inventoryservice = inventoryservice;
    }

    @GetMapping("/{productId}")
    public InventoryDto getInventoryByProductId(@PathVariable Integer productId) {
        return inventoryservice.fetchInventoryByProductId(productId);
    }
}
