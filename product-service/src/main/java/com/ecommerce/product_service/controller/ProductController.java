package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.cleint.InventoryClient;
import com.ecommerce.product_service.dto.ProductWithInventoryDto;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.response.ApiResponse;
import com.ecommerce.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Tag(name = "Product API", description = "Operations related to products")
public class ProductController {

    private final ProductService productService;
    private final InventoryClient inventoryClient;

    public ProductController(ProductService productService, InventoryClient inventoryClient) {
        this.productService = productService;
        this.inventoryClient = inventoryClient;
    }

    @GetMapping
    @Operation(summary = "Get All Products")
    public Map<String, List<Product>> getAllProducts() {
        return Map.of("products", productService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Product by Id")
    public ApiResponse<Product> getProduct(@PathVariable Long id) {
        try {
            Optional<Product> product = productService.findById(id);
            return product.map(ApiResponse::success).orElseGet(() -> ApiResponse.error("Product not found"));
        } catch (Exception e) {
            return ApiResponse.error("Product not found");
        }
    }

    @PostMapping
    public ApiResponse<String> createProduct(@RequestBody Product product) {
        Integer id = productService.create(product);
        return ApiResponse.success("Product created with ID: " + id);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update Product")
    public ApiResponse<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            return ApiResponse.success(productService.update(id, product));
        } catch (Exception e) {
            return ApiResponse.error("Update failed");
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.delete(id);
            return ApiResponse.success(null);
        } catch (Exception e) {
            return ApiResponse.error("Delete failed");
        }
    }

    @GetMapping("/{id}/inventory")
    @Operation(summary = "Get Product with Inventory Details")
    public Mono<ProductWithInventoryDto> getProductWithInventory(@PathVariable Long id) {
        Optional<Product> productOpt = productService.findById(id);
        if (productOpt.isEmpty()) {
            return Mono.error(new RuntimeException("Product not found"));
        }
        Product product = productOpt.get();
        return inventoryClient.fetchInventoryByProductId(id)
                .map(inventory -> new ProductWithInventoryDto(product, inventory));
    }
}
