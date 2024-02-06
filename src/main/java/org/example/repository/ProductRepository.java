package org.example.repository;
// Класс для обработки операций с базой данных класса продукта
import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
