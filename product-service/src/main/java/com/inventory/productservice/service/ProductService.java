package com.inventory.productservice.service;

import com.inventory.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
    List<Product> getAll();
    Product getById(Long id);
}
