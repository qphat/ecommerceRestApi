package com.example.ecommerce.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    String uploadImage(MultipartFile file, Long productId, String imageName) throws
            IOException;

    byte[] getImage(String fileName);

    String getImageName(Long productId);

}