/*
 * Spring Boot Docker Microservices - Product Service
 * 
 * https://github.com/egalli64/dockerized-microservices
 */
package com.example.product;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductRepository repo;
    private final RabbitTemplate rabbit;

    public ProductService(ProductRepository repo, RabbitTemplate rabbit) {
        this.repo = repo;
        this.rabbit = rabbit;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return repo.findById(id);
    }

    public List<Product> searchProductsByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsInStock() {
        return repo.findByStockGreaterThan(0);
    }

    @Transactional
    public Product createProduct(Product product) {
        Product saved = repo.save(product);
        rabbit.convertAndSend("product.exchange", "product.created", saved.getId());

        return saved;
    }

    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());

        return repo.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }

        repo.deleteById(id);
        rabbit.convertAndSend("product.exchange", "product.deleted", id);
    }

    /**
     * Listen to user events
     * <p>
     * TODO: implement logic (e.g.: deactivate products owned by deleted user)
     * 
     * @param userId
     */
    @RabbitListener(queues = "product.user.queue")
    public void handleUserEvent(Long userId) {
        System.out.println("Product service received user event for user: " + userId);
    }
}
