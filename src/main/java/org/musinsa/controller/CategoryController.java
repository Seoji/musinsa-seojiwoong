package org.musinsa.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.musinsa.model.dto.category.CategoryDto;
import org.musinsa.service.category.ICategory;


@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    ICategory categoryService;

    @GetMapping(value="/{pid}")
    public List<CategoryDto.CategoryReadDto> readCategoryByPid(
        @PathVariable(required = false) final int pid
    ) {
        return categoryService.readCategoryByPid(pid);
    }

    @GetMapping(value="/")
    public List<CategoryDto.CategoryReadDto> readCategory(
    ) {
        return categoryService.readCategory();
    }


}

