package org.musinsa.model.dao.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.musinsa.model.dto.category.CategoryDto.CategoryAllDto;

public interface CategoryAdminRepository extends JpaRepository<CategoryAllDto, Long> {
    List<CategoryAllDto> findAll();
    Optional<CategoryAllDto> findOneById(int id);
    void deleteById(int id);
}
