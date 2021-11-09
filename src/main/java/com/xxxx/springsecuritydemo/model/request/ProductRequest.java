package com.xxxx.springsecuritydemo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: ProductRequest
 * @date: 2021/4/28
 * @description:
 */
@Getter
@Setter

public class ProductRequest {


    String title;

    String category;

    int price;

    @JsonProperty( "origin_price")
    int originPrice;

    String unit;
//
    String image;

    String description;

    String content;

    @JsonProperty( "is_enabled")
    boolean enabled;

    int imageId;


}
