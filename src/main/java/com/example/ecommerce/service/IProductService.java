package com.example.ecommerce.service;

import com.example.ecommerce.model.dto.ProductDTO;
import com.example.ecommerce.model.payload.ProductResponse;

import java.util.List;

public interface IProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductDTO getProductById(Long id);
    ProductDTO updatePostById(Long id, ProductDTO productDTO);
    String deleteById(Long id);
    List<ProductDTO> getCategoriesByCategoryId(Long categoryId);
}
