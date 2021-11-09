package com.xxxx.springsecuritydemo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: CartRequest
 * @date: 2021/10/4
 * @description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    @JsonProperty("product_id")
    int id;

    int qty;

}
