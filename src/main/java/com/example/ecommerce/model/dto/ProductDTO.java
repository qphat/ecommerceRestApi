package com.example.ecommerce.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    @NotEmpty(message = "Product name should not be empty")
    private String name;

    @NotEmpty(message = "Product description should not be empty")
    @Size(min = 10, message = "Product description has at least 10 characters")
    private String desc;

    @NotEmpty(message = "Stock keeping unit should not be empty")
    private String sku;

    @Min(value = 0, message = "Price should be greater than or equal to 0")
    private Double price;

    private Long quantity;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private Long categoryId;

    @NotEmpty(message = "Product image should not be empty")
    private String imgSrc;
}
