package org.example.service;

import org.example.model.ProductAvailability;
import org.example.repository.ProductAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAvailabilityService {
    @Autowired
    private ProductAvailabilityRepository productAvailabilityRepository;

    public void setProductAvailability(Long productId, int amount) {
        ProductAvailability availability = new ProductAvailability();
        availability.setProductId(productId);
        availability.setAmount(amount);
        productAvailabilityRepository.save(availability);
    }

    public void deleteProductAvailability(Long productId) {
        productAvailabilityRepository.deleteById(productId);
    }
}

