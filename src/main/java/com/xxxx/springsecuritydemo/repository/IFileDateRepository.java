package com.xxxx.springsecuritydemo.repository;

import com.xxxx.springsecuritydemo.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IFileDateRepository extends JpaRepository<FileData,Integer> {

    @Override
    FileData getOne(Integer fileDataId);
}

//@Repository
//public interface IProductRepository extends JpaRepository<Product,Integer> {
//
//    //    @Query(value = "select com.xxxx.springsecuritydemo.model.response.ProductResponse(Product) from Product")
//    Page<ProductResponse>  findAllBy(Pageable pageable);
//
//
//    Optional<Product> findProductById(Integer productId);
//
//
////    long deleteBy(Integer id);
//}
