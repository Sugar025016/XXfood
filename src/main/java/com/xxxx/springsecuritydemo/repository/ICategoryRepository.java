//package com.xxxx.springsecuritydemo.repository;
//
//import com.example.demo.entity.Category;
//import com.example.demo.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface ICategoryRepository extends JpaRepository<Category, Integer> {
//
//    @Override
//    Category getOne(Integer id);
//
//    boolean existsByCategoryName(String categoryName);
//
//    Optional<Category> findByCategoryId(Integer categoryId);
//
////    @Query(value = "DELETE order_history_user FROM order_history " +
////            "JOIN order_history_user ON order_history.id=order_history_user.order_history_id WHERE order_history.group_id=:groupId AND order_history_user.user_id=:userId AND (order_history.order_id = :orderId OR :orderId IS NUll )", nativeQuery = true)
////    void deleteOrderHistory(@Param("orderId") Integer orderId, @Param("groupId") int groupId, @Param("userId") int userId);
//
////    User findUserByUsername(String username);
//
//}
