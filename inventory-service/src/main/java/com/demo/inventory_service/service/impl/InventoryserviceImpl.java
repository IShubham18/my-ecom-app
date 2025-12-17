package com.demo.inventory_service.service.impl;

import com.demo.inventory_service.dto.InventoryDto;
import com.demo.inventory_service.entity.Inventory;
import com.demo.inventory_service.repository.InventoryRepository;
import com.demo.inventory_service.service.Inventoryservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InventoryserviceImpl implements Inventoryservice {

    private final InventoryRepository inventoryRepository;
    private final Logger log = LoggerFactory.getLogger(InventoryserviceImpl.class);

    public InventoryserviceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryDto fetchInventoryByProductId(Integer productId) {

        Inventory inventory = inventoryRepository.findByProductId(productId);

        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setInventoryId(inventory.getInventoryId());
        inventoryDto.setProductId(inventory.getProductId());
        inventoryDto.setAvailable(inventory.getAvailable());
        inventoryDto.setReserved(inventory.getReserved());
        inventoryDto.setWarehouse(inventory.getWarehouse());



        log.info("Fetched inventory for product ID {}: {}", productId, inventoryDto);
        return inventoryDto;
    }
}
