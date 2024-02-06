package org.example.controller;

import org.example.model.Product;
import org.example.model.ProductAvailability;
import org.example.repository.ProductAvailabilityRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Контроллер операций, связанных с продуктом
@RestController
@RequestMapping("/products")
public class ProductController {
    // Тут поклоняемся схеме:
    // Пользователь -> Контроллер -> Репозиторий
    // Само приложение -> Сервис -> Репозиторий
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductAvailabilityRepository productAvailabilityRepository;

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            Optional<ProductAvailability> availabilityOptional =
                    productAvailabilityRepository.findById(product.getId());
            availabilityOptional.ifPresent(availability ->
                    product.setAvailability(availability.getAmount()));
        });
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            Optional<ProductAvailability> availabilityOptional =
                    productAvailabilityRepository.findById(id);
            availabilityOptional.ifPresent(availability ->
                    product.setAvailability(availability.getAmount()));
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            Product updatedProduct = productRepository.save(product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
