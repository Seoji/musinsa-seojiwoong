package org.musinsa.service.category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.musinsa.model.dao.category.CategoryAdminRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import org.musinsa.config.exception.ErrorCode;
import org.musinsa.config.exception.GeneralException;
import org.musinsa.model.dao.category.CategoryRepository;
import org.musinsa.model.dto.category.CategoryDto;


@AllArgsConstructor
@Service
public class CategoryImpl implements ICategory{
    private CategoryRepository categoryRepository;
    private CategoryAdminRepository categoryAdminRepository;

    public  List<CategoryDto.CategoryReadDto> readCategoryByPid(int pid) {
        List<CategoryDto.CategoryReadDto> categoryList = categoryRepository.findAllByPid(pid);

        if (categoryList.isEmpty()) {
            return categoryList;
        } else {
            categoryList
                .addAll(
                        categoryList
                        .stream()
                        .flatMap(category -> {
                            return readCategoryByPid(category.getId()).stream();
                            })
                        .collect(Collectors.toList())
            );

            return categoryList;
        }
    };

    public  List<CategoryDto.CategoryReadDto> readCategory() {
        return categoryRepository.findAll();
    };

    public  List<CategoryDto.CategoryAllDto> readCategoryAll() {
        List<CategoryDto.CategoryAllDto> categoryList = categoryAdminRepository.findAll();

        return categoryList;
    };

    public List<CategoryDto.CategoryAllDto> createCategory(CategoryDto.CategoryAllDto categoryDto) {
        try {
            categoryAdminRepository.save(categoryDto);
            return this.readCategoryAll();
        } catch (DataIntegrityViolationException E) {
            throw new GeneralException("DUPLICATES CATEGORY", ErrorCode.DUPLICATE_REQUEST);
        }
    };

    @Transactional
    public  List<CategoryDto.CategoryAllDto> updateCategory(int id, CategoryDto.CategoryAllDto categoryDto) {
        Optional<CategoryDto.CategoryAllDto> targetDto = categoryAdminRepository.findOneById(id);

        targetDto.orElseThrow(() -> new GeneralException("NOT FOUND CATEGORY", ErrorCode.NOT_FOUND));

        targetDto.get().setPid(categoryDto.getPid());
        targetDto.get().setText(categoryDto.getText());

        return this.readCategoryAll();
    };

    @Transactional
    public  List<CategoryDto.CategoryAllDto> deleteCategory(int id) {
        try {
            categoryAdminRepository.deleteById(id);
            return this.readCategoryAll();
        } catch (DataIntegrityViolationException E) {
            throw new GeneralException("CANT DELETE CATEGORY", ErrorCode.INVALID_REQUEST);
        }
    }
}
