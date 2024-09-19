package com.example.ecommerce.repository;

import com.example.ecommerce.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IImageRepository extends
        JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

    Optional<Image> findByProductId(Long productId);
}
