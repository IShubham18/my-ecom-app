package com.ecommerce.product_service.dto;

import lombok.Getter;
import lombok.Setter;

public class InventoryDto {
        private Integer inventoryId;
        private Integer productId;
        private Integer available;
        private Integer reserved;
        private String warehouse;

        public Integer getInventoryId() {
                return inventoryId;
        }

        public void setInventoryId(Integer inventoryId) {
                this.inventoryId = inventoryId;
        }

        public Integer getProductId() {
                return productId;
        }

        public void setProductId(Integer productId) {
                this.productId = productId;
        }

        public Integer getAvailable() {
                return available;
        }

        public void setAvailable(Integer available) {
                this.available = available;
        }

        public Integer getReserved() {
                return reserved;
        }

        public void setReserved(Integer reserved) {
                this.reserved = reserved;
        }

        public String getWarehouse() {
                return warehouse;
        }

        public void setWarehouse(String warehouse) {
                this.warehouse = warehouse;
        }
}
