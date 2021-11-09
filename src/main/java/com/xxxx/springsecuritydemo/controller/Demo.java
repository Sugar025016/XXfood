package com.xxxx.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Demo
 * @date: 2021/8/30
 * @description:
 */
@RestController
//@CrossOrigin(origins = "http://localhost:8081")
//@Validated
@RequestMapping("/api")
public class Demo {
    @RequestMapping(path="/" , method = RequestMethod.POST )
    public String getLogin(){
        System.out.println("有鬼阿..........=.=+，1");

        return "login";
    }
    @RequestMapping(path="/" , method = RequestMethod.GET )
    public String getLogin2(){
        System.out.println("有鬼阿..........=.=+， 2");

        return "login";
    }
}
