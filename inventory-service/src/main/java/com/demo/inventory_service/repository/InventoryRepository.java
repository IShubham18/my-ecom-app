package com.demo.inventory_service.repository;

import com.demo.inventory_service.dto.InventoryDto;
import com.demo.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductId(Integer productId);
}
