package org.example.repository;
// Класс для обработки операций с базой данных класса доступность продукта
import org.example.model.ProductAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAvailabilityRepository extends JpaRepository<ProductAvailability, Long> {
}

