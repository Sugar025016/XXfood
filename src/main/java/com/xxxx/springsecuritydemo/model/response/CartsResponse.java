package com.xxxx.springsecuritydemo.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * @author: CartsResponse
 * @date: 2021/10/20
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartsResponse {


    Long total;

    @JsonProperty("final_total")
    Long finalTotal;

    @JsonProperty("carts")
    List<CartResponse> cartResponseList;

    public CartsResponse(List<CartResponse> cartResponseList) {
        this.cartResponseList = cartResponseList;
        finalTotal=cartResponseList.stream().mapToLong(CartResponse::getTotal).sum();
        total=cartResponseList.stream().mapToLong(CartResponse::getTotalOriginPrice).sum();
    }
}
