package com.example.ecommerce.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotEmpty(message = "Category name should not be empty")
    private String name;

    @NotEmpty(message = "Category description should not be empty")
    @Size(min = 10)
    private String desc;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
