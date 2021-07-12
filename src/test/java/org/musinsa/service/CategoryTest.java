package org.musinsa.service;

import jdk.jfr.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.musinsa.config.exception.GeneralException;
import org.musinsa.model.dto.category.CategoryDto;
import org.musinsa.service.category.CategoryImpl;
import org.musinsa.model.dao.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryTest {

    @Autowired
    CategoryImpl categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    private int parentCategoryId;
    private Integer parentCategoryPid = null;
    private String parentCategoryText = "root";


    @BeforeAll
    void beforeTestAll() {
        this.parentCategoryId = Long.valueOf(categoryRepository.count()).intValue();
    }

    @BeforeEach
    void beforeTest() {
        categoryRepository.deleteAll();

        CategoryDto.CategoryAllDto newCategory = new CategoryDto.CategoryAllDto();
        newCategory.setPid(this.parentCategoryPid);
        newCategory.setText(this.parentCategoryText);
        List<CategoryDto.CategoryAllDto> categoryList = categoryService.createCategory(newCategory);

        parentCategoryId = categoryList.get(0).getId();
    }

    @Test
    public void testCreateRead() {

        int childCategoryId = parentCategoryId+1;
        int childCategoryPid = parentCategoryId;
        String childCategoryText = "children1";
        CategoryDto.CategoryAllDto childCategory = new CategoryDto.CategoryAllDto();
        childCategory.setId(childCategoryId);
        childCategory.setPid(childCategoryPid);
        childCategory.setText(childCategoryText);
        System.out.println(childCategory);
        List<CategoryDto.CategoryAllDto> currentCategoryList = categoryService.createCategory(childCategory);

        assertThat(currentCategoryList.get(1).getId()).isEqualTo(childCategoryId);
        assertThat(currentCategoryList.get(1).getPid()).isEqualTo(childCategoryPid);
        assertThat(currentCategoryList.get(1).getText()).isEqualTo(childCategoryText);

        childCategoryId = parentCategoryId+2;
        childCategoryPid = parentCategoryId;
        childCategoryText = "children2";
        childCategory =  new CategoryDto.CategoryAllDto();
        childCategory.setId(childCategoryId);
        childCategory.setPid(childCategoryPid);
        childCategory.setText(childCategoryText);

        currentCategoryList = categoryService.createCategory(childCategory);

        assertThat(currentCategoryList.get(2).getId()).isEqualTo(childCategoryId);
        assertThat(currentCategoryList.get(2).getPid()).isEqualTo(childCategoryPid);
        assertThat(currentCategoryList.get(2).getText()).isEqualTo(childCategoryText);

        List<CategoryDto.CategoryReadDto> categoryList = categoryService.readCategory();
        assertThat(categoryList.get(0).getId()).isEqualTo(parentCategoryId);
        assertThat(categoryList.get(0).getPid()).isEqualTo(parentCategoryPid);
        assertThat(categoryList.get(0).getText()).isEqualTo(parentCategoryText);
        assertThat(categoryList.size()).isEqualTo(3);

        List<CategoryDto.CategoryReadDto> categoryReadList = categoryService.readCategoryByPid(childCategoryPid);
        assertThat(categoryReadList.get(0).getId()).isNotEqualTo(parentCategoryId);
        assertThat(categoryReadList.get(0).getPid()).isNotEqualTo(parentCategoryPid);
        assertThat(categoryReadList.get(0).getText()).isNotEqualTo(parentCategoryText);

        assertThat(categoryReadList.get(0).getPid()).isEqualTo(parentCategoryId);

        assertThat(categoryReadList.get(1).getId()).isEqualTo(childCategoryId);
        assertThat(categoryReadList.get(1).getPid()).isEqualTo(childCategoryPid);
        assertThat(categoryReadList.get(1).getText()).isEqualTo(childCategoryText);
        assertThat(categoryReadList.size()).isEqualTo(2);
    }

    @Test
    public void testUpdate() {

        CategoryDto.CategoryAllDto newCategory = new CategoryDto.CategoryAllDto();
        String updatedCategoryText = "test2";

        newCategory.setText(updatedCategoryText);

        List<CategoryDto.CategoryAllDto> currentCategoryList = categoryService.updateCategory(parentCategoryId, newCategory);

        assertThat(currentCategoryList.get(0).getId()).isEqualTo(parentCategoryId);
        assertThat(currentCategoryList.get(0).getText()).isEqualTo(updatedCategoryText);

    }

    @Test
    public void testDelete() {
        int childCategoryId = parentCategoryId+1;
        int childCategoryPid = parentCategoryId;
        String childCategoryText = "children1";
        CategoryDto.CategoryAllDto childCategory = new CategoryDto.CategoryAllDto();
        childCategory.setId(childCategoryId);
        childCategory.setPid(childCategoryPid);
        childCategory.setText(childCategoryText);

        List<CategoryDto.CategoryAllDto> currentCategoryList = categoryService.createCategory(childCategory);

        assertThat(currentCategoryList.get(1).getId()).isEqualTo(childCategoryId);
        assertThat(currentCategoryList.get(1).getPid()).isEqualTo(childCategoryPid);
        assertThat(currentCategoryList.get(1).getText()).isEqualTo(childCategoryText);

        childCategoryId = parentCategoryId+2;
        childCategoryPid = parentCategoryId;
        childCategoryText = "children2";
        childCategory =  new CategoryDto.CategoryAllDto();
        childCategory.setId(childCategoryId);
        childCategory.setPid(childCategoryPid);
        childCategory.setText(childCategoryText);

        currentCategoryList = categoryService.createCategory(childCategory);

        assertThat(currentCategoryList.get(2).getId()).isEqualTo(childCategoryId);
        assertThat(currentCategoryList.get(2).getPid()).isEqualTo(childCategoryPid);
        assertThat(currentCategoryList.get(2).getText()).isEqualTo(childCategoryText);

        currentCategoryList = categoryService.deleteCategory(childCategoryId);

        assertThat(currentCategoryList.size()).isEqualTo(2);

        Throwable thrown = catchThrowable(() -> categoryService.deleteCategory(parentCategoryId));
        assertThat(thrown).isInstanceOf(GeneralException.class);
    }
}