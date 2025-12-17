package com.ecommerce.product_service.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WebClientConfig.class);
    @Bean
    public WebClient inventoryWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8081")

                .build();
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();

        registry.getAllCircuitBreakers().forEach(cb ->
                cb.getEventPublisher()
                        .onStateTransition(event ->
                                log.warn("CircuitBreaker {} state changed from {} to {}",
                                        event.getCircuitBreakerName(),
                                        event.getStateTransition().getFromState(),
                                        event.getStateTransition().getToState())
                        )
        );
        return registry;
    }


   /* @Bean
    public WebClient inventoryWebClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
                // 2 seconds to establish a TCP connection
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                // 2 seconds max waiting for response
                .responseTimeout(Duration.ofSeconds(2))
                // Optional: if no data arrives after connection
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(2))
                                .addHandlerLast(new WriteTimeoutHandler(2))
                );

        return builder
                .baseUrl("http://localhost:8081")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }*/



}
