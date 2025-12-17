package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.Product;
import lombok.NoArgsConstructor;


public class ProductWithInventoryDto {
    Product product;
    InventoryDto inventory;

    public ProductWithInventoryDto() {
    }

    public ProductWithInventoryDto(Product product, InventoryDto inventory) {
        this.product = product;
        this.inventory = inventory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InventoryDto getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDto inventory) {
        this.inventory = inventory;
    }
}
