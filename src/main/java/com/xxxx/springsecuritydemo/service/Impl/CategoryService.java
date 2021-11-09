//package com.xxxx.springsecuritydemo.service.Impl;
//
//import com.example.demo.entity.Category;
//import com.example.demo.repository.ICategoryRepository;
//import com.example.demo.service.ICategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author: CategoryService
// * @date: 2021/4/10
// * @description:
// */
//@Transactional
//@Service
//public class CategoryService implements ICategoryService {
//
//    @Autowired
//    ICategoryRepository iCategoryRepository;
//
//    @Override
//    public Category saveCategory(Category category) {
//        return iCategoryRepository.save(category);
//    }
//
//
//    @Override
//    public Category getCategory(Integer categoryId) {
//        Optional<Category> byId = iCategoryRepository.findByCategoryId(categoryId);
//        return byId.orElse(null);
//    }
//
//    @Override
//    public List<Category> getAll( ) {
//
//
//
//        return iCategoryRepository.findAll();
//    }
//
//    @Override
//    public void deleteCategory(Integer CategoryId) {
//
//        iCategoryRepository.deleteById(CategoryId);
//    }
//}
