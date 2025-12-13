package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product update(Long id, Product product);

    void delete(Long id);

    Integer create(Product product);
}
