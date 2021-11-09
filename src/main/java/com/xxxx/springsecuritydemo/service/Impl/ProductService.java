package com.xxxx.springsecuritydemo.service.Impl;

import com.xxxx.springsecuritydemo.entity.FileData;
import com.xxxx.springsecuritydemo.entity.Product;
import com.xxxx.springsecuritydemo.model.request.ProductRequest;
import com.xxxx.springsecuritydemo.model.response.ProductResponse;
import com.xxxx.springsecuritydemo.repository.IFileDateRepository;
import com.xxxx.springsecuritydemo.repository.IProductRepository;
import com.xxxx.springsecuritydemo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author: ProductService
 * @date: 2021/4/4
 * @description:
 */
@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    IProductRepository iProductRepository;
    @Autowired
    IFileDateRepository iFileDateRepository;
    @Value("${imageGetUrl}")
    String imageGetUrl;
    @Value("${imagePutUrl}")
    String imagePutUrl;


    @Override
    public Page<ProductResponse> getProduct(Pageable pageable) {

        Page<ProductResponse> all = iProductRepository.findAllBy(pageable);

        return all;
    }

    @Override
    public Page<ProductResponse> getProduct(boolean enabled, Pageable pageable) {
        Page<ProductResponse> allByEnabled = iProductRepository.findAllByEnabled(enabled, pageable);
        allByEnabled.getContent().stream().forEach(v->{
            v.setImageUrl( v.getImageUrl()!=null? imageGetUrl+v.getImageUrl():null);
        });
        return allByEnabled;
    }

    @Override
    public Product getProduct(Integer pageableId) {
        return iProductRepository.findProductById(pageableId).orElse(null);
    }

    @Override
    public ProductResponse getProductBy(Integer id) {
        Optional<ProductResponse> productByIdAndEnabled = iProductRepository.findProductByIdAndEnabled(id, true);
        ProductResponse productResponse = new ProductResponse();
        if(productByIdAndEnabled.isPresent()){
            productResponse = productByIdAndEnabled.get();
            productResponse.setImageUrl(productResponse.getImageUrl()!=null?(imageGetUrl+productResponse.getImageUrl()):null);
        }

        return productResponse;
    }

    @Override
    public Product saveProduct(Product product) {


        return iProductRepository.save(product);
    }

    @Override
    public Product changeProduct(ProductRequest productRequest,int id) {
        iProductRepository.deleteById(id);
        Product product = addProduct(productRequest);
        return product;
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {

        FileData fileData = iFileDateRepository.findById(productRequest.getImageId()).orElse(null);
        Product product = new Product(productRequest,fileData);
        Product save = iProductRepository.save(product);
        return save;
    }

    @Override
    public Product deleteProduct(int id) {
        iProductRepository.deleteById(id);


        return null;
    }



}
