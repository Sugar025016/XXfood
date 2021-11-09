package com.xxxx.springsecuritydemo.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xxxx.springsecuritydemo.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: ProductResponse
 * @date: 2021/4/27
 * @description:
 */
@Getter
@Setter
public class ProductResponse {

    private Integer id;

    private String title;

    private String category;

    private Integer price;


    @JsonProperty("origin_price")
    private Integer originPrice;

    private String description;

    private String unit;

    private String imageUrl;

    private int imageId;

    private String content;

    @JsonProperty("is_enabled")
    private boolean enabled;



    public ProductResponse() {
    }

    public ProductResponse(Product product) {
        BeanUtils.copyProperties(product,this);

        if(product.getFileData()!=null){
            this.imageUrl=product.getFileData().getFileName();
            this.imageId=product.getFileData().getId();
        }


    }

    public void ProductResponse(Product product) {
        BeanUtils.copyProperties(product,this);

        if(product.getFileData()!=null){
            this.imageUrl=product.getFileData().getFileName()+product.getFileData().getSuffix();
            this.imageId=product.getFileData().getId();
        }
    }


}
