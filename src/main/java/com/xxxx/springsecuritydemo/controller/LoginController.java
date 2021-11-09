package com.xxxx.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: LoginController
 * @date: 2021/8/27
 * @description:
 */
@Controller
@RequestMapping("/api")
public class LoginController {
    @RequestMapping(path="/login" , method = RequestMethod.GET )
    public String login(){
        return "redirect:login.html";
    }
}
