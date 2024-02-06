package org.example.controller;

import org.example.service.ProductAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-availability")
public class ProductAvailabilityController {
    @Autowired
    private ProductAvailabilityService productAvailabilityService;
    // Тут поклоняемся схеме:
    // Пользователь -> Контроллер -> Сервис -> Репозиторий
    // Само приложение -> Сервис -> Репозиторий
    @PostMapping("/{productId}")
    public ResponseEntity<Void> setProductAvailability(@PathVariable Long productId, @RequestParam int amount) {
        productAvailabilityService.setProductAvailability(productId, amount);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductAvailability(@PathVariable Long productId) {
        productAvailabilityService.deleteProductAvailability(productId);
        return ResponseEntity.noContent().build();
    }
}

