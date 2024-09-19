package com.example.ecommerce.service;

import com.example.ecommerce.model.dto.CategoryDTO;
import com.example.ecommerce.model.payload.CategoryResponse;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);

    CategoryDTO getCategoryById(Long id);

    CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO);

    String deleteCategoryById(Long id);
}
