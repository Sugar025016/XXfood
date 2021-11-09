package com.xxxx.springsecuritydemo.service;

import com.xxxx.springsecuritydemo.entity.User;
import com.xxxx.springsecuritydemo.model.Response;
import com.xxxx.springsecuritydemo.model.request.CartRequest;
import com.xxxx.springsecuritydemo.model.response.CartResponse;
import com.xxxx.springsecuritydemo.model.response.CartsResponse;

import java.util.List;

/**
 * @author: ICartService
 * @date: 2021/10/4
 * @description:
 */

public interface ICartService {
    CartsResponse getByUser(User user);

    Response addCart(CartRequest cartRequest, User user);

    Response deleteCart(int id, User user);


}
