package com.example.ecommerce;

import com.example.ecommerce.controller.ImageController;
import com.example.ecommerce.service.IImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ImageControllerTest {

    @InjectMocks
    private ImageController imageController;

    @Mock
    private IImageService iImageService;

    @Mock
    private MultipartFile file;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadImageSuccess() throws IOException {
        // Arrange
        Long productId = 1L;
        String imageName = "test-image.png";
        String uploadResponse = "Image uploaded successfully";
        when(iImageService.uploadImage(file, productId, imageName)).thenReturn(uploadResponse);

        // Act
        ResponseEntity<?> response = imageController.uploadImage(file, productId, imageName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(uploadResponse, response.getBody());
    }

    @Test
    void testDownloadImageSuccess() {
        // Arrange
        String fileName = "test-image.png";
        byte[] imageData = new byte[] {1, 2, 3, 4};
        when(iImageService.getImage(fileName)).thenReturn(imageData);

        // Act
        ResponseEntity<?> response = imageController.downloadImage(fileName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.valueOf("image/png"), response.getHeaders().getContentType());
        assertEquals(imageData, response.getBody());
    }

    @Test
    void testGetImageByProductIdSuccess() {
        // Arrange
        Long productId = 1L;
        String imageName = "test-image.png";
        when(iImageService.getImageName(productId)).thenReturn(imageName);

        // Act
        ResponseEntity<String> response = imageController.getImageByProductId(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(imageName, response.getBody());
    }
}
