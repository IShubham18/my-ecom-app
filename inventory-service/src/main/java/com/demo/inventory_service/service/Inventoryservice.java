package com.demo.inventory_service.service;

import com.demo.inventory_service.dto.InventoryDto;

public interface Inventoryservice {

    InventoryDto fetchInventoryByProductId(Integer productId);
}
