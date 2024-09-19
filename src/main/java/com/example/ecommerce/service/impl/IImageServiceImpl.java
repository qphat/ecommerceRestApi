package com.example.ecommerce.service.impl;

import com.example.ecommerce.exception.ECommerceAPIException;
import com.example.ecommerce.utlis.ImageUtils;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.entity.Image;
import com.example.ecommerce.model.entity.Product;
import com.example.ecommerce.repository.IImageRepository;
import com.example.ecommerce.repository.IProductRepository;
import com.example.ecommerce.service.IImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class IImageServiceImpl implements IImageService {

    @Autowired
    private IImageRepository iImageRepository;

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String uploadImage(MultipartFile file, Long productId, String imageName) throws IOException {
        Product product = iProductRepository.findById(productId)
                                            .orElseThrow(() -> new ResourceNotFoundException("Product", "id",
                                                                                             String.valueOf(

                                                                                                     productId)));
        Optional<Image> optional = iImageRepository.findByName(imageName);
        if (optional.isEmpty()) {
            Image image = iImageRepository.save(Image.builder()
                    .name(imageName)
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .product(product)
                    .build());
            return "file uploaded successfully: " + image.getName();
        }

        throw new ResourceNotFoundException("Image exist in database", "Image name", imageName);
    }

    @Override
    public byte[] getImage(String fileName) {
        Image dbImageData = iImageRepository.findByName(fileName)
                                            .orElseThrow(() -> new ResourceNotFoundException("Image", "File name",
                                                                                             fileName));
        return ImageUtils.decompressImage(dbImageData.getImageData());
    }

    @Override
    public String getImageName(Long productId) {
        Image image = iImageRepository.findByProductId(productId)
                .orElseThrow(() -> new ECommerceAPIException(HttpStatus.BAD_REQUEST,
                                                                        "Image not found with product id"));
        return image.getName();
    }
}
