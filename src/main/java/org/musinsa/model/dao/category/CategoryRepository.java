package org.musinsa.model.dao.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.musinsa.model.dto.category.CategoryDto.CategoryReadDto;

public interface CategoryRepository extends JpaRepository<CategoryReadDto, Long> {
    List<CategoryReadDto> findAllByPid(int pid);
}
