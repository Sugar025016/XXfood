package com.xxxx.springsecuritydemo.repository;

import com.xxxx.springsecuritydemo.entity.Product;
import com.xxxx.springsecuritydemo.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    //    @Query(value = "select com.xxxx.springsecuritydemo.model.response.ProductResponse(Product) from Product")
    Page<ProductResponse> findAllBy(Pageable pageable);

    Page<ProductResponse> findAllByEnabled(boolean enabled,Pageable pageable);


    Optional<Product> findProductById(Integer productId);

    Optional<ProductResponse> findProductByIdAndEnabled(Integer productId,boolean enabled);


//    long deleteBy(Integer id);
}