package org.musinsa.component;

import org.springframework.stereotype.Component;

import org.musinsa.model.dao.category.CategoryRepository;
import org.musinsa.model.dto.category.CategoryDto;

import java.util.List;

@Component
public class CategorySingleton {

    private CategoryRepository categoryRepository;

    private List<CategoryDto.CategoryReadDto> categoryList;

    public CategorySingleton (CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryList = this.categoryRepository.findAll();
    };

    public List<CategoryDto.CategoryReadDto> getCategoryList () {
        return this.categoryList;
    }

    public void refreshCategoryList () {
        this.categoryList = this.categoryRepository.findAll();
    }

}
