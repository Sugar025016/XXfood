package com.xxxx.springsecuritydemo.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.xxxx.springsecuritydemo.entity.Cart;
import com.xxxx.springsecuritydemo.entity.Product;
import com.xxxx.springsecuritydemo.entity.User;
import com.xxxx.springsecuritydemo.enums.Rcode;
import com.xxxx.springsecuritydemo.model.Response;
import com.xxxx.springsecuritydemo.model.request.CartRequest;
import com.xxxx.springsecuritydemo.model.request.UserRequest;
import com.xxxx.springsecuritydemo.model.response.ProductResponse;
import com.xxxx.springsecuritydemo.service.Impl.CartService;
import com.xxxx.springsecuritydemo.service.Impl.FileService;
import com.xxxx.springsecuritydemo.service.Impl.ProductService;
import com.xxxx.springsecuritydemo.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Controller
 * @date: 2021/2/28
 * @description:
 */

@RestController
@RequestMapping("/api")
//@Validated
public class ApiController {
    @Autowired
    ProductService productService;
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @RequestMapping(path="/products", method = RequestMethod.GET )
    public Response getProducts(@PageableDefault(size = 9,page=0) Pageable pageable ){

//        List<SchedulesResponse> scheduleList = sckeduleService.getSchedules(startDate,endDate);
        System.out.println("成功********");
        System.out.println(pageable);
        Page<ProductResponse> productPage = productService.getProduct(true,pageable);

        return new Response(Rcode.Success,productPage);
    }

    @RequestMapping(path="/product/{id}", method = RequestMethod.GET )
    public Response getProduct(@PathVariable int id){

        System.out.println("成功********");
        System.out.println(id);
        ProductResponse productBy = productService.getProductBy(id);


        return new Response(Rcode.Success,productBy);
    }





//    @RequestMapping(path="/showLogin1" , method = RequestMethod.GET )
//    public Response getLogin(HttpServletRequest request){
//        System.out.println("有鬼阿..........=.=+，");
//        HttpSession session = request.getSession();
////        session.get
//        return new Response();
//    }

//    @Autowired
//    IUserService iUserService;
//
//    @Autowired
//    IProductService iProductService;

//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    public String getHome(@PageableDefault Pageable pageable, Model model)  {
//
//        System.out.println("home123465798");
//        Page<Product> product = iProductService.getProduct(pageable);
//
////        model.addAttribute("product",product.getContent().get(0));
//        model.addAttribute("product",product.getContent());
//
//
//
//        return "home";
//    }

    @RequestMapping(path="/showLogin1" , method = RequestMethod.GET )
    public String getLogin(){
        System.out.println("有鬼阿..........=.=+，");

        return "login";
    }

//    @RequestMapping(value = "/product", method = RequestMethod.GET)
//    public String getProduct(@PageableDefault Pageable pageable, Model model,@ModelAttribute("header") String header)  {
//
//        System.out.println("product123465798");
//        System.out.println(header);
//        Page<Product> product = iProductService.getProduct(pageable);
////        model.addAttribute("product",product.getContent().get(0));
//        model.addAttribute("product",product.getContent());
//
//        return "product";
//    }
//
//
//    @ModelAttribute("header")
//    public String getHeader()  {
//
//        System.out.println("header123465798");
//        return "header";
//    }
//
//
//
//    @RequestMapping(path="/login", method = RequestMethod.POST)
//    public String login(){
//
//
//        return "login";
//    }
//
    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Response addUser(@RequestBody()@JsonProperty( "user") UserRequest userRequest, Authentication authentication)  {

        System.out.println("123465798");
        System.out.println(authentication.getPrincipal());
        System.out.println("123465798");
        System.out.println("123465798");
        System.out.println("123465798");
        Response add = userService.add(userRequest);

        return add;
    }
//    @ResponseBody
//    @RequestMapping(value = "/get_user/{id}", method = RequestMethod.GET)
//    public UserResponse addUser(@PathVariable int id, HttpServletRequest request)  {
//
//
//        UserResponse userResponse = iUserService.get(id);file_data
//        HttpSession session = request.getSession();
//
//        return userResponse;
//    }



//    @ResponseBody
//    @RequestMapping(value = "/cart", method = RequestMethod.POST)
//    public Response addCart(@RequestBody() CartRequest cartRequest,HttpServletRequest httpServletRequest)  {
//
//        System.out.println("123465798");
////        Cart cart = new Cart(cartRequest);
//
//
//        return new Response(Rcode.Success);
//    }



}
