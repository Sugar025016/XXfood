package com.xxxx.springsecuritydemo.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: CategoryRequest
 * @date: 2021/4/25
 * @description:
 */
@Data
public class CategoryRequest {


    Integer categoryId;

    @NotBlank
    String categoryName;
}
