package com.ecommerce.product_service.cleint;

import com.ecommerce.product_service.dto.InventoryDto;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InventoryClient {
    private final WebClient webClient;
    private final Logger log = LoggerFactory.getLogger(InventoryClient.class);

    public InventoryClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "inventoryServiceCB", fallbackMethod = "inventoryFallback")
    public Mono<InventoryDto> fetchInventoryByProductId(Long productid) {
        log.info("Fetching inventory for product ID: {}", productid);
        log.info("WebClient instance: {}", webClient);
       /* return webClient.get()
                .uri("/api/v1/inventory/{productId}", productid)
                .retrieve()
                .bodyToMono(InventoryDto.class);*/
        return webClient.get()
                .uri("/api/v1/inventory/{productId}", productid)
                .retrieve()
                .bodyToMono(InventoryDto.class);

    }

    public Mono<InventoryDto> inventoryFallback(Long productid, Throwable throwable) {
        InventoryDto fallbackInventory = new InventoryDto();
        fallbackInventory.setProductId(productid.intValue());
        fallbackInventory.setAvailable(0);
        fallbackInventory.setReserved(0);
        fallbackInventory.setWarehouse("Unknown");
        return Mono.just(fallbackInventory);
    }

}
