package com.xxxx.springsecuritydemo.repository;

import com.xxxx.springsecuritydemo.entity.Cart;
import com.xxxx.springsecuritydemo.entity.User;
import com.xxxx.springsecuritydemo.model.response.CartResponse;
import com.xxxx.springsecuritydemo.model.response.CartsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: CartRepository
 * @date: 2021/10/4
 * @description:
 */
@Repository
public interface ICartRepository extends JpaRepository<Cart,Integer> {

    List<CartResponse> getCartByUser(User user);

    @Modifying
    long deleteByIdAndUser(int id ,User user);

}
