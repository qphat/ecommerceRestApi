package com.example.ecommerce.service.impl;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.dto.CategoryDTO;
import com.example.ecommerce.model.entity.Category;
import com.example.ecommerce.model.payload.CategoryResponse;
import com.example.ecommerce.repository.ICategoryRepository;
import com.example.ecommerce.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryServiceImpl
        implements ICategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = iCategoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy)
                .ascending() : Sort.by(sortBy)
                .descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Category> categories = iCategoryRepository.findAll(pageable);

        List<Category> listOfPosts = categories.getContent();
        List<CategoryDTO> content = listOfPosts.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse response = new CategoryResponse();
        response.setContent(content);
        response.setPageNo(categories.getNumber());
        response.setPageSize(categories.getSize());
        response.setTotalElements(categories.getTotalElements());
        response.setTotalPages(categories.getTotalPages());
        response.setLast(categories.isLast());

        return response;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = findCategory(id);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO) {
        Category category = findCategory(id);
        category.setDesc(categoryDTO.getDesc());
        category.setName(categoryDTO.getName());
        Category updatedCategory = iCategoryRepository.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public String deleteCategoryById(Long id) {
        iCategoryRepository.deleteById(id);
        return "Deleted Successfully";
    }

    private Category findCategory(Long id) {
        return iCategoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category", "id", String.valueOf(id)));
    }
}
