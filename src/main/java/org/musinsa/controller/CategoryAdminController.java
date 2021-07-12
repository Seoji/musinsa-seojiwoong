package org.musinsa.controller;

import java.util.List;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.musinsa.model.dto.category.CategoryDto;
import org.musinsa.service.category.ICategory;

@RestController
@RequestMapping("category/admin")
@RequiredArgsConstructor
public class CategoryAdminController {
    @Autowired
    ICategory categoryService;

    @GetMapping(value="/")
    public List<CategoryDto.CategoryAllDto> readCategory(
    ) {
        return categoryService.readCategoryAll();
    }

    @PostMapping
    public List<CategoryDto.CategoryAllDto> createCategory (final CategoryDto.CategoryAllDto categoryDto) {
        System.out.println(categoryDto);
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping(value="/{id}")
    public List<CategoryDto.CategoryAllDto> updateCategory(
            @PathVariable final int id,
            @RequestBody @Valid final CategoryDto.CategoryAllDto categoryDto
    ) {
        System.out.println(categoryDto);
        return categoryService.updateCategory(id, categoryDto);
    }

    @DeleteMapping(value="/{id}")
    public List<CategoryDto.CategoryAllDto> deleteCategory (
            @PathVariable final int id
    ) {
        return categoryService.deleteCategory(id);
    }
}
