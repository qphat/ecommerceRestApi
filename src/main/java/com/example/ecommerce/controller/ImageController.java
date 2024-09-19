package com.example.ecommerce.controller;


import com.example.ecommerce.repository.IImageRepository;
import com.example.ecommerce.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private IImageService iImageService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,
                                         @RequestParam("productId") Long productId,
                                         @RequestParam("name") String imageName) throws
            IOException {
        String uploadImage = iImageService.uploadImage(file, productId, imageName);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = iImageService.getImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.valueOf("image/png"))
                             .body(imageData);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<String> getImageByProductId(@PathVariable("productId") Long id) {
        return ResponseEntity.ok(iImageService.getImageName(id));
    }
}
