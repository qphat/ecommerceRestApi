package com.example.ecommerce;

import com.example.ecommerce.controller.CategoryController;
import com.example.ecommerce.model.dto.CategoryDTO;
import com.example.ecommerce.model.payload.CategoryResponse;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.utlis.AppConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private ICategoryService iCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategorySuccess() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();
        when(iCategoryService.createCategory(categoryDTO)).thenReturn(categoryDTO);

        // Act
        ResponseEntity<CategoryDTO> response = categoryController.createCategory(categoryDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryDTO, response.getBody());
    }

    @Test
    void testGetAllCategories() {
        // Arrange
        CategoryResponse categoryResponse = new CategoryResponse();
        when(iCategoryService.getAllCategories(1, 10, "name", "asc")).thenReturn(categoryResponse);

        // Act
        ResponseEntity<CategoryResponse> response = categoryController.getAllCategories(1, 10, "name", "asc");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryResponse, response.getBody());
    }

    @Test
    void testGetCategoryByIdSuccess() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();
        Long id = 1L;
        when(iCategoryService.getCategoryById(id)).thenReturn(categoryDTO);

        // Act
        ResponseEntity<CategoryDTO> response = categoryController.getCategoryById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDTO, response.getBody());
    }

    @Test
    void testUpdateCategoryByIdSuccess() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();
        Long id = 1L;
        when(iCategoryService.updateCategoryById(id, categoryDTO)).thenReturn(categoryDTO);

        // Act
        ResponseEntity<CategoryDTO> response = categoryController.updateCategoryById(id, categoryDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDTO, response.getBody());
    }

    @Test
    void testDeleteCategoryByIdSuccess() {
        // Arrange
        Long id = 1L;
        String message = "Category deleted successfully";
        when(iCategoryService.deleteCategoryById(id)).thenReturn(message);

        // Act
        ResponseEntity<String> response = categoryController.deleteCategoryById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
    }
}
