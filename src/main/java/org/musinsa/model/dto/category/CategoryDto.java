package org.musinsa.model.dto.category;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class CategoryDto {
    @Entity
    @Table(name="musinsaCategory")
    @Data
    @NoArgsConstructor
    public static class CategoryAllDto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @ApiModelProperty(hidden=true)
        int id;
        Integer pid;
        @NotEmpty
        String text;

        @CreatedDate
        @ApiModelProperty(hidden=true)
        @Column(nullable = true, insertable = false, updatable = false)
        Date createdDate;

        @LastModifiedDate
        @ApiModelProperty(hidden=true)
        @Column(nullable= true, insertable = false, updatable = false)
        Date updatedDate;
    }

    @Entity
    @Table(name="musinsaCategory")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryReadDto {
        @Id
        int id;
        Integer pid;
        String text;
    }
}




