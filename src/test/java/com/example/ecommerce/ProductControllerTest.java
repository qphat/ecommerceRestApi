package com.example.ecommerce;

import com.example.ecommerce.controller.ProductController;
import com.example.ecommerce.model.dto.ProductDTO;
import com.example.ecommerce.model.payload.ProductResponse;
import com.example.ecommerce.service.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private IProductService iProductService;

    private ProductDTO productDTO;
    private ProductResponse productResponse;
    private List<ProductDTO> productList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Product Name");

        productResponse = new ProductResponse();
        productResponse.setContent(List.of(productDTO));
        productResponse.setPageNo(1);
        productResponse.setPageSize(1);
        productResponse.setTotalPages(1);

        productList = List.of(productDTO);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        when(iProductService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);

        // Act
        ResponseEntity<?> response = productController.createProduct(productDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        when(iProductService.getAllProducts(anyInt(), anyInt(), anyString(), anyString())).thenReturn(productResponse);

        // Act
        ResponseEntity<ProductResponse> response = productController.getAllProducts(1, 10, "name", "asc");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productResponse, response.getBody());
    }

    @Test
    void testGetProductById() {
        // Arrange
        when(iProductService.getProductById(anyLong())).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.getProductById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    void testUpdateProductById() {
        // Arrange
        when(iProductService.updatePostById(anyLong(), any(ProductDTO.class))).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.updateProductById(1L, productDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    void testDeleteProductById() {
        // Arrange
        String successMessage = "Product deleted successfully";
        when(iProductService.deleteById(anyLong())).thenReturn(successMessage);

        // Act
        ResponseEntity<String> response = productController.deleteProductById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successMessage, response.getBody());
    }

    @Test
    void testGetProductsByCategoryId() {
        // Arrange
        when(iProductService.getCategoriesByCategoryId(anyLong())).thenReturn(productList);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.getProductsByCategoryId(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }
}
