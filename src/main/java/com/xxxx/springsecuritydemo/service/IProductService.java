package com.xxxx.springsecuritydemo.service;

import com.xxxx.springsecuritydemo.entity.Product;
import com.xxxx.springsecuritydemo.model.request.ProductRequest;
import com.xxxx.springsecuritydemo.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author: IProductService
 * @date: 2021/4/4
 * @description:
 */

public interface IProductService {

     Page<ProductResponse> getProduct(Pageable pageable);
     Page<ProductResponse> getProduct(boolean enabled,Pageable pageable);
     Product getProduct(Integer pageableId);
     ProductResponse getProductBy(Integer pageableId);
     Product saveProduct(Product product);
     Product changeProduct(ProductRequest product,int id);
     Product addProduct(ProductRequest product);
     Product deleteProduct(int id);
}
