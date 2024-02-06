package org.example;
// Основная точка входа для Spring'а
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Этот класс включает аннотацию @SpringBootApplication
// — удобную аннотацию, объединяющую @Configuration,
// @EnableAutoConfiguration и @ComponentScan.

// @Configuration помечает класс как класс конфигурации с определениями компонентов.
// @EnableAutoConfiguration включает автоматическую настройку на основе пути к классам.
// @ComponentScan включает сканирование компонентов, позволяя Spring
// обнаруживать и регистрировать bean-компоненты в указанных пакетах.
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}