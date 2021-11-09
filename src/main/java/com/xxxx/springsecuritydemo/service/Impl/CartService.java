package com.xxxx.springsecuritydemo.service.Impl;

import com.xxxx.springsecuritydemo.entity.Cart;
import com.xxxx.springsecuritydemo.entity.Product;
import com.xxxx.springsecuritydemo.entity.User;
import com.xxxx.springsecuritydemo.enums.Rcode;
import com.xxxx.springsecuritydemo.model.Response;
import com.xxxx.springsecuritydemo.model.request.CartRequest;
import com.xxxx.springsecuritydemo.model.response.CartResponse;
import com.xxxx.springsecuritydemo.model.response.CartsResponse;
import com.xxxx.springsecuritydemo.repository.ICartRepository;
import com.xxxx.springsecuritydemo.repository.IProductRepository;
import com.xxxx.springsecuritydemo.repository.IUserRepository;
import com.xxxx.springsecuritydemo.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author: CartService
 * @date: 2021/10/4
 * @description:
 */
@Service
@Transactional
public class CartService implements ICartService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private ICartRepository iCartRepository;




    @Override
    public CartsResponse getByUser(User user) {
        List<CartResponse> cartByUser = iCartRepository.getCartByUser(user);
        CartsResponse cartsResponse = new CartsResponse(cartByUser);
        return cartsResponse;
    }

    @Override
    public Response addCart(CartRequest cartRequest, User user) {
        Optional<Product> byId = iProductRepository.findById(cartRequest.getId());
        if(!byId.isPresent()){
            return new Response(Rcode.Error);
        }

        Product product = byId.get();
        Cart cart = new Cart(product,user,cartRequest.getQty());
        Cart save = iCartRepository.save(cart);

        return new Response(Rcode.Success,save);
    }

    @Override
    public Response deleteCart(int id, User user) {
        long l = iCartRepository.deleteByIdAndUser(id, user);
        System.out.println("delete:"+l);
        return new Response(Rcode.Success);
    }
}
