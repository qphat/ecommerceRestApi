package com.example.ecommerce.repository;

import com.example.ecommerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends
        JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Optional<Product> findByCategoryId(Long categoryId);
}

