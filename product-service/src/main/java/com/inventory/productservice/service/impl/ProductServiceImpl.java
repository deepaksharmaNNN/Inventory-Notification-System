package com.inventory.productservice.service.impl;

import com.inventory.productservice.dto.LowStockEvent;
import com.inventory.productservice.entity.Product;
import com.inventory.productservice.repository.ProductRepository;
import com.inventory.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final KafkaTemplate<String, LowStockEvent> kafkaTemplate;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setSku(product.getSku());
                    existingProduct.setQuantity(product.getQuantity());
                    existingProduct.setReorderLevel(product.getReorderLevel());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public Product updateProductQuantity(String skuCode, int quantity) {
        Product product = productRepository.findBySku(skuCode)
                .orElseThrow(() -> new RuntimeException("Product not found with SKU: " + skuCode));
        product.setQuantity(quantity);
        Product updatedProduct = productRepository.save(product);
        if (updatedProduct.getQuantity() <= updatedProduct.getReorderLevel()) {
            LowStockEvent event = new LowStockEvent(
                    updatedProduct.getSku(),
                    updatedProduct.getQuantity(),
                    updatedProduct.getReorderLevel()
            );
            kafkaTemplate.send("low-stock-topic", event);
        }
        return updatedProduct;
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        productRepository::delete,
                        () -> { throw new RuntimeException("Product not found with id: " + id); }
                );
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
}
