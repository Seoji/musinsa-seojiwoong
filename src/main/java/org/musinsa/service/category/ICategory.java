package org.musinsa.service.category;

import java.util.List;

import org.musinsa.model.dto.category.CategoryDto;

public interface ICategory {
    List<CategoryDto.CategoryReadDto> readCategory();
    List<CategoryDto.CategoryReadDto> readCategoryByPid(int pid);
    List<CategoryDto.CategoryAllDto> readCategoryAll();
    List<CategoryDto.CategoryAllDto> createCategory(CategoryDto.CategoryAllDto categoryDto);

    List<CategoryDto.CategoryAllDto> updateCategory(int id, CategoryDto.CategoryAllDto categoryDto);
    List<CategoryDto.CategoryAllDto> deleteCategory(int id);
}
